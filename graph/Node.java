package graph;

import java.util.*;

import graph.ex.InvalidEdgeException;

/**
 * Represents a node in the graph.
 * 
 * @param <T> The type of the node.
 */
public interface Node<T>
{
	/**
	 * Retrieves the collection of edges.
	 * 
	 * @return an iterator with all the accessible edges.
	 */
	public Iterator<Edge<T>> getEdges();

	/**
	 * Retrieves an edge from the collection based on a given id value.
	 * 
	 * @param id The id of the node.
	 * @return the edge corresponding to the given id, and null otherwise.
	 */
	public Edge<T> getEdge(T id);

	/**
	 * Adds the edge object to the edges collection, using the destId as the key.
	 * 
	 * @param edge The edge we want to add to the collection of edges.
	 * @throws InvalidEdgeException if the edge is invalid.
	 */
	public void addEdge(Edge<T> edge) throws InvalidEdgeException;

	/**
	 * Gets the id of the node.
	 * 
	 * @return the id of the node.
	 */
	public T getId();
}
