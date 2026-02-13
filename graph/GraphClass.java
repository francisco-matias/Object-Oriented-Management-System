package graph;

import java.util.*;

/**
 * Represents the graph.
 *
 * @param <T> The type of the nodes.
 * @param <N> The node specification. 
 * @param <E> The edge specification.
 */
public class GraphClass<T, N extends Node<T>, E extends Edge<T>> implements Graph<T, N, E>
{
	protected List<N> nodes;
	protected List<E> edges;
	
	/**
	 * The constructor.
	 * 
	 * @param nodes The list of all nodes presented in the graph.
	 * @param edges The list of all edges presented in the graph.
	 */
	public GraphClass(List<N> nodes, List<E> edges)
	{
		this.nodes = nodes;
		this.edges = edges;
	}

	@Override
	public int getNodeNumber()
	{
		return nodes.size();
	}

	@Override
	public Iterator<N> getNodes()
	{
		return nodes.iterator();
	}

	@Override
	public N getNode(T nodeId)
	{
		for (N node : nodes)
			if (node.getId().equals(nodeId))
				return node;
		return null;
	}

	@Override
	public int getEdgeNumber()
	{
		return edges.size();
	}

	@Override
	public Iterator<E> getEdges()
	{
		return edges.iterator();
	}
}
