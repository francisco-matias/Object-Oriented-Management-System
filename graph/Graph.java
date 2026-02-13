package graph;

import java.util.*;

/**
 * Represents the graph.
 *
 * @param <T> The type of the nodes.
 * @param <N> The node specification. 
 * @param <E> The edge specification.
 */
public interface Graph<T, N extends Node<T>, E extends Edge<T>>
{
	/**
	 * Gets the number of nodes presented in the graph.
	 * 
	 * @return the number of nodes in the graph.
	 */
	public int getNodeNumber();

	/**
	 * Retrieves the collection of nodes.
	 * 
	 * @return an iterator with all the nodes that are accessible.
	 */
	public Iterator<N> getNodes();

	/**
	 * Gets a node in a collection of nodes based on a given nodeId.
	 * 
	 * @param nodeId The id of the node.
	 * @return a node if a node with the matching nodeId is found in the
	 *         collection. Otherwise, it returns null.
	 */
	public N getNode(T nodeId);

	/**
	 * Gets the number of edges presented in the graph.
	 * 
	 * @return the number of edges in the graph.
	 */
	public int getEdgeNumber();

	/**
	 * Retrieves the collection of edges.
	 * 
	 * @return an iterator with all the edges in the graph.
	 */
	public Iterator<E> getEdges();

}
