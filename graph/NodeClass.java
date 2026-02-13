package graph;

import java.util.*;

import graph.ex.*;

/**
 * Represents a node in the graph.
 * 
 * @param <T> The type of the node.
 */
public class NodeClass<T> implements Node<T>
{
	private Map<T, Edge<T>> edges;
	private T id;
	
	/**
	 * The constructor.
	 * 
	 * @param id The id of the node.
	 */
	public NodeClass(T id)
	{
		edges = new HashMap<T, Edge<T>>();
		this.id = id;
	}

	@Override
	public Iterator<Edge<T>> getEdges()
	{
		return edges.values().iterator();
	}

	@Override
	public Edge<T> getEdge(T id)
	{
		return edges.get(id);
	}

	@Override
	public void addEdge(Edge<T> edge) throws InvalidEdgeException
	{
		if (edge == null)
			throw new InvalidEdgeException();

		Node<T> destination = edge.getDestination();

		if (destination == null)
			throw new InvalidEdgeException();

		T destId = destination.getId();
		edges.put(destId, edge);
	}

	@Override
	public T getId()
	{
		return id;
	}
}
