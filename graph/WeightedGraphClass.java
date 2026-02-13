package graph;

import java.util.*;

/**
 * Represents a graph with weighted edges.  
 *
 * @param <T> The type of the nodes.
 * @param <N> The node specification. 
 * @param <E> The edge specification.
 */
public class WeightedGraphClass<T, N extends Node<T>, E extends WeightedEdge<T>> extends GraphClass<T, N, E>
		implements WeightedGraph<T, N, E>
{
	private int totalWeight;
	
	/**
	 * The constructor.
	 * 
	 * @param nodes The list of all nodes presented in the graph.
	 * @param edges The list of all edges presented in the graph.
	 */
	public WeightedGraphClass(List<N> nodes, List<E> edges)
	{
		super(nodes, edges);
		totalWeight = getGraphWeight();
	}

	private int getGraphWeight()
	{
		int weight = 0;

		for (E edge : edges)
			weight += edge.getWeight();

		return weight;
	}

	@Override
	public int getTotalWeight()
	{
		return totalWeight;
	}
}
