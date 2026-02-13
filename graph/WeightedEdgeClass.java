package graph;

import graph.ex.*;

/**
 * Represents an weighted edge with a cost and attractiveness related to its traversing.
 *
 * @param <T> The type of the nodes.
 */
public class WeightedEdgeClass<T> extends EdgeClass<T> implements WeightedEdge<T>
{
	protected int weight;
	protected double attractiveness;
	
	/**
	 * The constructor.
	 * 
	 * @param source The source node in an edge.
	 * @param destination The destination node in an edge.
	 * @param weight The weight in an edge.
	 * @throws InvalidWeightException if the weight of the edge is invalid.
	 */
	public WeightedEdgeClass(Node<T> source, Node<T> destination, int weight) throws InvalidWeightException
	{
		super(source, destination);

		setWeight(weight);
		attractiveness = 0;
	}
	
	/**
	 * The constructor.
	 * 
	 * @param weight The weight in an edge.
	 * @throws InvalidWeightException if the weight of the edge is invalid.
	 */
	public WeightedEdgeClass(int weight) throws InvalidWeightException
	{
		this(null, null, weight);
	}

	@Override
	public void setWeight(int weight) throws InvalidWeightException
	{
		if (weight <= 0)
			throw new InvalidWeightException();

		this.weight = weight;
	}

	@Override
	public void setAttractiveness(double attractiveness)
	{
		this.attractiveness = attractiveness;
	}

	@Override
	public void decreaseAttractiveness(double units)
	{
		double result = attractiveness - units;
		if (result < 0)
			attractiveness = 0;
		else
			attractiveness = result;
	}

	@Override
	public int getWeight()
	{
		return weight;
	}

	@Override
	public double getAttractiveness()
	{
		return attractiveness;
	}
}
