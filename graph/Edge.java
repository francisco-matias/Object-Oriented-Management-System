package graph;

import graph.ex.*;

/**
 * Represents an edge.
 *
 * @param <T> The type of the node.
 */
public interface Edge<T>
{
	/**
	 * Sets the source in an edge.
	 * 
	 * @param source The source node in an edge.
	 * @throws InvalidNodeException if the source is invalid.
	 */
	public void setSource(Node<T> source) throws InvalidNodeException;

	/**
	 * Sets the destination in an edge.
	 * 
	 * @param destination The destination node in an edge.
	 * @throws InvalidNodeException if the destination node is invalid.
	 */
	public void setDestination(Node<T> destination) throws InvalidNodeException;

	/**
	 * Gets the source node of a certain edge.
	 * 
	 * @return the source node.
	 */
	public Node<T> getSource();

	/**
	 * Gets the destination node of a certain edge.
	 * 
	 * @return the destination node.
	 */
	public Node<T> getDestination();
}
