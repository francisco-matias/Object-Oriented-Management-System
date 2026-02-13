package graph;

import graph.ex.*;

/**
 * Represents an edge.
 *
 * @param <T> The type of the node.
 */
public class EdgeClass<T> implements Edge<T>
{
	protected Node<T> source, destination;
	
	/**
	 * The constructor.
	 * 
	 * @param source The source node in an edge.
	 * @param destination The destination node in an edge.
	 * @throws InvalidWeightException if the weight of the edge is invalid.
	 */
	public EdgeClass(Node<T> source, Node<T> destination) throws InvalidWeightException
	{
		this.source = source;
		this.destination = destination;
	}

	@Override
	public void setSource(Node<T> source) throws InvalidNodeException
	{
		if (source == null)
			throw new InvalidNodeException();
		this.source = source;
	}

	@Override
	public void setDestination(Node<T> destination) throws InvalidNodeException
	{
		if (source == null)
			throw new InvalidNodeException();
		this.destination = destination;
	}

	@Override
	public Node<T> getSource()
	{
		return source;
	}

	@Override
	public Node<T> getDestination()
	{
		return destination;
	}
}
