package sim;

import java.util.*;

import aco.*;
import aco.ex.*;
import graph.*;
import graph.ex.*;

/**
 * Represents the simulation.
 */
public class SimulationClass implements Simulation
{
	private WeightedGraph<Integer, Node<Integer>, WeightedEdge<Integer>> graph;
	private AntColony antColony;
	private EventContainer pec;

	private double alphaMoveEvent, betaMoveEvent, deltaMoveEvt, pheromoneEvapEvtEta, pheromoneEvapEvtRo, pheromoneLevel;
	private Node<Integer> nestNode;
	
	/**
	 * The constructor.
	 * 
	 * @param adjacencyMatrix The adjacency Matrix.
	 * @param nestNode The nest node.
	 * @param alphaMoveEvent Alpha, ant move event.
	 * @param betaMoveEvent Beta, ant move event.
	 * @param deltaMoveEvt Delta, ant move event.
	 * @param pheromoneEvapEvtEta Eta, pheromone evaporation event.
	 * @param pheromoneEvapEvtRo Ro, pheromone evaporation event.
	 * @param pheromoneLevel The pheromone level.
	 * @param antColonySize The ant colony size.
	 * @throws InvalidWeightException if when simulating there is an invalid weight.
	 * @throws InvalidEdgeException if when simulating there is an invalid edge.
	 * @throws InvalidNodeException if when simulating there is an invalid node.
	 */
	public SimulationClass(int[][] adjacencyMatrix, int nestNode, double alphaMoveEvent, double betaMoveEvent,
			double deltaMoveEvt, double pheromoneEvapEvtEta, double pheromoneEvapEvtRo, double pheromoneLevel,
			int antColonySize) throws InvalidWeightException, InvalidEdgeException, InvalidNodeException
	{
		graph = buildGraph(adjacencyMatrix);

		this.nestNode = graph.getNode(nestNode);
		this.alphaMoveEvent = alphaMoveEvent;
		this.betaMoveEvent = betaMoveEvent;
		this.deltaMoveEvt = deltaMoveEvt;
		this.pheromoneEvapEvtEta = pheromoneEvapEvtEta;
		this.pheromoneEvapEvtRo = pheromoneEvapEvtRo;
		this.pheromoneLevel = pheromoneLevel;

		antColony = getAntColony(antColonySize);
		pec = new PendingEventContainer();
	}

	private WeightedGraph<Integer, Node<Integer>, WeightedEdge<Integer>> buildGraph(int[][] adjacencyMatrix)
			throws InvalidWeightException, InvalidEdgeException, InvalidNodeException
	{
		List<WeightedEdge<Integer>> allEdges = new LinkedList<>();
		List<Node<Integer>> allNodes = new ArrayList<>(adjacencyMatrix.length);

		// Create the nodes.
		for (int i = 0; i < adjacencyMatrix.length; i++)
		{
			Node<Integer> node = new NodeClass<Integer>(i + 1);
			allNodes.add(node);
		}

		// Create the edges.
		for (int i = 0; i < adjacencyMatrix.length; i++)
		{
			for (int j = 0; j < adjacencyMatrix[i].length; j++)
			{
				int weight = adjacencyMatrix[i][j];

				if (i == j || weight == 0)
					continue;

				Node<Integer> source = allNodes.get(i);
				Node<Integer> destination = allNodes.get(j);

				WeightedEdge<Integer> edge = new WeightedEdgeClass<>(source, destination, weight);
				allEdges.add(edge);

				source.addEdge(edge);
			}
		}

		return new WeightedGraphClass<>(allNodes, allEdges);
	}

	private AntColony getAntColony(int antColonySize)
	{
		AntColony colony = new AntColonyClass(this.nestNode, alphaMoveEvent, betaMoveEvent, deltaMoveEvt,
				pheromoneEvapEvtEta, pheromoneEvapEvtRo, pheromoneLevel, graph.getTotalWeight(), graph.getNodeNumber());
		int left = antColonySize;

		while (left-- > 0)
			colony.addAnt();

		return colony;
	}

	@Override
	public void init() throws InvalidPathException
	{
		Iterator<Ant> ants = antColony.getAnts();

		while (ants.hasNext())
		{
			Ant ant = ants.next();
			Iterator<Event> events = ant.getNextEvents();

			while (events.hasNext())
				pec.addEvent(events.next());
		}
	}

	@Override
	public void run() throws InvalidPathException
	{
		Event event = pec.getNextEvent();
		// Will be false if the event didn't happened yet.
		boolean nextIsPast = event != null ? event.hasHappened() : false;

		while (nextIsPast)
		{
			event = pec.removeNextEvent();

			Iterator<Event> nextEvts = pec.simulateEvent(event);
			while (nextEvts.hasNext())
				pec.addEvent(nextEvts.next());

			event = pec.getNextEvent();
			nextIsPast = event != null ? event.hasHappened() : false;
		}
	}

	@Override
	public Iterator<AntCycle> getTopCycles(int topNum)
	{
		List<AntCycle> topCycles = new LinkedList<>();
		Iterator<AntCycle> cycles = antColony.getCycles();

		// Exclude the best cycle from the list.
		if (cycles.hasNext())
			cycles.next();

		for (int i = 0; i < topNum && cycles.hasNext(); i++)
			topCycles.add(cycles.next());

		return topCycles.iterator();
	}

	@Override
	public AntCycle getBestCycle()
	{
		AntCycle bestCycle = null;

		Iterator<AntCycle> cycles = antColony.getCycles();
		if (cycles.hasNext())
			bestCycle = cycles.next();

		return bestCycle;
	}

	@Override
	public WeightedGraph<Integer, Node<Integer>, WeightedEdge<Integer>> getGraph()
	{
		return graph;
	}

	@Override
	public EventContainer getPEC()
	{
		return pec;
	}
}
