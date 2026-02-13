package graph;

import graph.ex.*;

/**
 * Represents an weighted edge with a cost and attractiveness related to its traversing.
 *
 * @param <T> The type of the nodes.
 */
public interface WeightedEdge<T> extends Edge<T>
{
	/**
	 * Sets the value of the weight to a certain edge.
	 * 
	 * @param weight The weight of the corresponding edge.
	 * @throws InvalidWeightException if the weight is invalid.
	 */
	public void setWeight(int weight) throws InvalidWeightException;

	/**
	 * Sets the cost to a certain edge.
	 * 
	 * @param attractiveness The attractiveness of traversing this edge.
	 */
	public void setAttractiveness(double attractiveness);

	/**
	 * Decreases the attractiveness of a certain edge.
	 * 
	 * @param units Will decrease the attractiveness by the units passed.
	 */
	public void decreaseAttractiveness(double units);

	/**
	 * Gets the weight of the edge.
	 * 
	 * @return the weight presented in the edge.
	 */
	public int getWeight();

	/**
	 * Gets the attractiveness of traversing the edge.
	 * 
	 * @return the attractiveness of traversing the edge.
	 */
	public double getAttractiveness();
}
