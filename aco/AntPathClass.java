package aco;

import graph.*;

/**
 * Represents an Ant path.
 */
public class AntPathClass extends AntCycleClass implements AntPath
{
	/**
	 * The constructor.
	 */
	public AntPathClass()
	{
		super();
	}

	private int removeFromPath()
	{
		if (path.empty())
			return -1;

		Node<Integer> node = path.pop();
		Node<Integer> last = path.lastElement();
		WeightedEdge<Integer> edge = (WeightedEdge<Integer>) last.getEdge(node.getId());
		accumulatedWeight -= edge.getWeight();

		return node.getId();
	}

	private void removeCycleFromPath(int nodeId)
	{
		if (!pathContains(nodeId))
			return;

		while (getCurrent().getId() != nodeId)
			removeFromPath();
	}

	@Override
	public void addToPath(Node<Integer> node)
	{
		int nodeId = node.getId();

		if (!pathContains(nodeId))
		{
			if (path.size() > 0)
			{
				Node<Integer> previous = path.lastElement();
				WeightedEdge<Integer> edge = (WeightedEdge<Integer>) previous.getEdge(node.getId());
				accumulatedWeight += edge.getWeight();
			}

			path.push(node);
		}
		else
			removeCycleFromPath(nodeId);
	}
}
