package aco;

import java.util.*;

import aco.ex.*;
import graph.*;
import math.*;
import sim.*;

/**
 * Represents an Ant.
 */
public class AntClass implements Ant
{
	private AntPath path;
	private AntColony colony;
	private WeightedEdge<Integer> nextPath;
	private List<Event> subsequentEvts;

	/**
	 * The constructor.
	 * 
	 * @param colony The colony of Ants.
	 */
	public AntClass(AntColony colony)
	{
		this.colony = colony;
		nextPath = null;
		subsequentEvts = null;

		initPath();
	}

	private void initPath()
	{
		path = new AntPathClass();
		path.addToPath(colony.getNest());
	}

	private Event getMoveEvent()
	{
		int millis = (int) Distribution.exponentialDist(colony.getDeltaMoveEvent() * nextPath.getWeight()) * 1000;
		Date evtTime = Distribution.getDateFromNow(millis);

		return new MoveEvent(evtTime, this);
	}

	private WeightedEdge<Integer> getNextVisited(List<WeightedEdge<Integer>> visitedPaths) throws InvalidPathException
	{
		if (visitedPaths.isEmpty())
			throw new InvalidPathException();

		int pathIdx = (int) Distribution.uniformDist(visitedPaths.size());

		return visitedPaths.get(pathIdx);
	}

	private WeightedEdge<Integer> getNextNonVisited(List<WeightedEdge<Integer>> nonVisitedPaths) throws InvalidPathException
	{
		Random rand = new Random();
		int numNodes = nonVisitedPaths.size();
		double[] cValues = new double[numNodes];
		double cSum = 0;

		// Calculate cijk values and ci sum.
		for (int k = 0; k < numNodes; k++)
		{
			WeightedEdge<Integer> edge = nonVisitedPaths.get(k);
			double fijk = edge.getAttractiveness();
			int aijk = edge.getWeight();
			double cijk = (colony.getAlphaMoveEvent() + fijk) / (colony.getBetaMoveEvent() + aijk);
			cValues[k] = cijk;
			cSum += cijk;
		}

		// Calculate probabilities Pijk and select a node randomly.
		double cumulativeProbability = 0;
		for (int k = 0; k < numNodes; k++)
		{
			double probability = cValues[k] / cSum;
			cumulativeProbability += probability;

			// Check if the random value falls within the cumulative probability range.
			if (rand.nextDouble() <= cumulativeProbability)
				return nonVisitedPaths.get(k);
		}

		throw new InvalidPathException();
	}

	@Override
	public Iterator<Event> getNextEvents() throws InvalidPathException
	{
		if (subsequentEvts != null)
			return subsequentEvts.iterator();

		subsequentEvts = new LinkedList<Event>();

		Node<Integer> current = path.getCurrent();
		List<WeightedEdge<Integer>> visitedPaths = new LinkedList<>();
		List<WeightedEdge<Integer>> nonVisitedPaths = new LinkedList<>();
		Iterator<Edge<Integer>> possiblePaths = current.getEdges();

		while (possiblePaths.hasNext())
		{
			Edge<Integer> edge = possiblePaths.next();
			int nodeId = edge.getDestination().getId();

			if (!path.pathContains(nodeId))
				nonVisitedPaths.add((WeightedEdge<Integer>) edge);
			else
				visitedPaths.add((WeightedEdge<Integer>) edge);
		}

		// In case the ant can't proceed and needs to go back.
		if (nonVisitedPaths.isEmpty())
			nextPath = getNextVisited(visitedPaths);
		else
			nextPath = getNextNonVisited(nonVisitedPaths);

		Event nextMoveEvt = getMoveEvent();
		subsequentEvts.add(nextMoveEvt);

		// Verifies if the ant has completed a cycle.
		if (nextPath.getDestination().getId().equals(colony.getNest().getId())
				&& path.getPathSize() == colony.getGraphSize())
		{
			Iterator<Event> evapEvents = colony.addCycle(path);
			while (evapEvents.hasNext())
				subsequentEvts.add(evapEvents.next());
		}

		return subsequentEvts.iterator();
	}

	@Override
	public void move() throws InvalidPathException
	{
		if (nextPath == null)
			throw new InvalidPathException();

		Node<Integer> nextNode = nextPath.getDestination();
		path.addToPath(nextNode);

		nextPath = null;
		subsequentEvts = null;
	}
}
