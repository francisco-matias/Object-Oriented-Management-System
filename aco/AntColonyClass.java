package aco;

import java.util.*;

import graph.*;
import math.Distribution;
import sim.*;

/**
 * Represents a colony of Ants.
 */
public class AntColonyClass implements AntColony
{
	private List<Ant> antColony;
	private List<AntCycle> completedCycles;
	private Map<Integer, Event> pheromoneEvents;
	private Node<Integer> nest;

	private double alphaMoveEvt, betaMoveEvt, deltaMoveEvt, pheromoneEvapEvtEta, pheromoneEvapEvtRo, pheromoneLevel;
	private int graphWeight, graphSize;

	/**
	 * The constructor.
	 * 
	 * @param nest The nest node.
	 * @param alphaMoveEvt Alpha, ant move event.
	 * @param betaMoveEvt Beta, ant move event.
	 * @param deltaMoveEvt Delta, ant move event.
	 * @param pheromoneEvapEvtEta Eta, pheromone evaporation event.
	 * @param pheromoneEvapEvtRo Ro, pheromone evaporation event.
	 * @param pheromoneLevel Pheromone level.
	 * @param graphWeight The total weight of the graph.
	 * @param graphSize The total number of nodes in the graph.
	 */
	public AntColonyClass(Node<Integer> nest, double alphaMoveEvt, double betaMoveEvt, double deltaMoveEvt,
			double pheromoneEvapEvtEta, double pheromoneEvapEvtRo, double pheromoneLevel, int graphWeight,
			int graphSize)
	{
		antColony = new LinkedList<>();
		completedCycles = new ArrayList<>();
		pheromoneEvents = new HashMap<>();

		this.nest = nest;
		this.alphaMoveEvt = alphaMoveEvt;
		this.betaMoveEvt = betaMoveEvt;
		this.deltaMoveEvt = deltaMoveEvt;
		this.pheromoneEvapEvtEta = pheromoneEvapEvtEta;
		this.pheromoneEvapEvtRo = pheromoneEvapEvtRo;
		this.pheromoneLevel = pheromoneLevel;
		this.graphWeight = graphWeight;
		this.graphSize = graphSize;
	}

	@Override
	public boolean addAnt()
	{
		if (nest == null)
			return false;

		Ant ant = new AntClass(this);
		return addAnt(ant);
	}

	@Override
	public boolean addAnt(Ant ant)
	{
		if (ant == null)
			return false;

		antColony.add(ant);
		return true;
	}

	@Override
	public void killColony()
	{
		antColony.clear();
	}

	@Override
	public Iterator<Ant> getAnts()
	{
		return antColony.iterator();
	}

	// Checks if the cycle has already been discovered before.
	private boolean containsCycle(AntCycle newCycle)
	{
		for (AntCycle cycle : completedCycles)
		{
			if (cycle.compareTo(newCycle) < 0)
				continue;
			else if (cycle.compareTo(newCycle) == 0)
			{
				if (cycle.getPathSize() != newCycle.getPathSize())
					continue;

				Iterator<Integer> cycleNodes = cycle.getPathIds();
				Iterator<Integer> newCycleNodes = newCycle.getPathIds();
				boolean areEqual = true;

				while (cycleNodes.hasNext())
				{
					if (cycleNodes.next() != newCycleNodes.next())
					{
						areEqual = false;
						break;
					}
				}

				if (areEqual)
					return true;
			}
			else
				break;
		}

		return false;
	}

	private void layDownPheromones(Node<Integer> source, Node<Integer> destination, int pathWeight)
	{
		WeightedEdge<Integer> edge = (WeightedEdge<Integer>) source.getEdge(destination.getId());
		double pheromones = pheromoneLevel * graphWeight / pathWeight;
		edge.setAttractiveness(pheromones);
	}

	// Lays down pheromones in all the edges.
	private void layDownPheromones(AntCycle cycle)
	{
		Iterator<Node<Integer>> cycleIt = cycle.getPath();
		Node<Integer> previousNode = null;

		if (cycleIt.hasNext())
			previousNode = cycleIt.next();

		Node<Integer> firstNode = previousNode;

		while (cycleIt.hasNext())
		{
			Node<Integer> node = cycleIt.next();
			layDownPheromones(previousNode, node, cycle.getPathWeight());

			previousNode = node;
		}

		if (firstNode != null)
			layDownPheromones(previousNode, firstNode, cycle.getPathWeight());
	}

	private Event getEvaporationEvent(WeightedEdge<Integer> edge)
	{
		int millis = (int) Distribution.exponentialDist(pheromoneEvapEvtEta) * 1000;
		Date evtTime = Distribution.getDateFromNow(millis);
		return new EvaporationEvent(evtTime, this, edge);
	}

	private Event getEvaporationEvent(Node<Integer> source, Node<Integer> destination)
	{
		Edge<Integer> edge = source.getEdge(destination.getId());

		if (pheromoneEvents.containsKey(edge.hashCode()))
			return null;

		Event event = getEvaporationEvent((WeightedEdge<Integer>) edge);
		pheromoneEvents.put(edge.hashCode(), event);
		return event;
	}

	// Get the evaporation events.
	private List<Event> getEvaporationEvents(AntCycle cycle)
	{
		List<Event> events = new LinkedList<>();
		Iterator<Node<Integer>> cycleIt = cycle.getPath();
		Node<Integer> previousNode = null;

		if (cycleIt.hasNext())
			previousNode = cycleIt.next();

		Node<Integer> firstNode = previousNode;

		while (cycleIt.hasNext())
		{
			Node<Integer> node = cycleIt.next();

			Event event = getEvaporationEvent(previousNode, node);
			if (event != null)
				events.add(event);

			previousNode = node;
		}

		if (firstNode != null)
		{
			Event event = getEvaporationEvent(previousNode, firstNode);
			if (event != null)
				events.add(event);
		}

		return events;
	}

	@Override
	public Iterator<Event> addCycle(AntCycle cycle)
	{
		List<Event> events = new LinkedList<>();

		if (cycle == null)
			return events.iterator();

		AntCycle newCycle = new AntCycleClass(cycle);

		layDownPheromones(newCycle);

		if (containsCycle(newCycle))
			return getEvaporationEvents(newCycle).iterator();

		completedCycles.add(newCycle);
		// The list of cycles will always be ordered.
		Collections.sort(completedCycles);

		return getEvaporationEvents(newCycle).iterator();
	}

	@Override
	public void clearCycles()
	{
		completedCycles.clear();
	}

	@Override
	public Iterator<AntCycle> getCycles()
	{
		return completedCycles.iterator();
	}

	@Override
	public Iterator<Event> evaporatePheromones(WeightedEdge<Integer> edge)
	{
		List<Event> events = new LinkedList<>();

		edge.decreaseAttractiveness(pheromoneEvapEvtRo);
		if (edge.getAttractiveness() > 0)
		{
			Event event = getEvaporationEvent(edge);
			events.add(event);
		}
		else
			pheromoneEvents.remove(edge.hashCode());

		return events.iterator();
	}

	@Override
	public Node<Integer> getNest()
	{
		return nest;
	}

	@Override
	public double getAlphaMoveEvent()
	{
		return alphaMoveEvt;
	}

	@Override
	public double getBetaMoveEvent()
	{
		return betaMoveEvt;
	}

	@Override
	public double getDeltaMoveEvent()
	{
		return deltaMoveEvt;
	}

	@Override
	public double getPheromoneEventEta()
	{
		return pheromoneEvapEvtEta;
	}

	@Override
	public double getPheromoneEventRo()
	{
		return pheromoneEvapEvtRo;
	}

	@Override
	public double getPheromoneLevel()
	{
		return pheromoneLevel;
	}

	@Override
	public int getGraphWeight()
	{
		return graphWeight;
	}

	@Override
	public int getGraphSize()
	{
		return graphSize;
	}
}
