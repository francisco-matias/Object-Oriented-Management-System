package graph;

/**
 * Represents a graph with weighted edges.  
 *
 * @param <T> The type of the nodes.
 * @param <N> The node specification. 
 * @param <E> The edge specification.
 */
public interface WeightedGraph<T, N extends Node<T>, E extends WeightedEdge<T>> extends Graph<T, N, E>
{

	/**
	 * Gets the total weight presented in the graph.
	 * 
	 * @return The sum of the weight of all the edges.
	 */
	public int getTotalWeight();
}
