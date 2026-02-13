package aco;

import java.util.*;

import graph.*;

/**
 * Represents a Hamiltonian Cycle traversed by the Ant. 
 */
public class AntCycleClass implements AntCycle
{
	protected Stack<Node<Integer>> path;
	protected int accumulatedWeight;

	protected AntCycleClass()
	{
		path = new Stack<>();
		accumulatedWeight = 0;
	}

	/**
	 * The constructor.
	 * 
	 * @param antPath The Hamiltonian cycle of an Ant. 
	 */
	public AntCycleClass(AntCycle antPath)
	{
		this();

		Iterator<Node<Integer>> nodes = antPath.getPath();
		while (nodes.hasNext())
			path.push(nodes.next());
		accumulatedWeight = antPath.getPathWeight();

		Node<Integer> nest = path.get(0);
		Node<Integer> last = path.peek();
		WeightedEdge<Integer> closingEdge = (WeightedEdge<Integer>) last.getEdge(nest.getId());
		accumulatedWeight += closingEdge.getWeight();
	}

	@Override
	public int getPathWeight()
	{
		return accumulatedWeight;
	}

	@Override
	public Iterator<Integer> getPathIds()
	{
		List<Integer> pathIds = new LinkedList<>();

		for (Node<Integer> node : path)
			pathIds.add(node.getId());

		return pathIds.iterator();
	}

	@Override
	public Iterator<Node<Integer>> getPath()
	{
		return path.iterator();
	}

	@Override
	public Node<Integer> getCurrent()
	{
		return path.peek();
	}

	@Override
	public boolean pathContains(int nodeId)
	{
		for (Node<Integer> node : path)
			if (node.getId() == nodeId)
				return true;
		return false;
	}

	@Override
	public int getPathSize()
	{
		return path.size();
	}

	@Override
	public int compareTo(AntCycle o)
	{
		return accumulatedWeight < o.getPathWeight() ? -1 : accumulatedWeight == o.getPathWeight() ? 0 : 1;
	}
}
