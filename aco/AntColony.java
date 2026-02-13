package aco;

import java.util.*;

import graph.*;
import sim.*;

/**
 * Represents a colony of Ants.
 */
public interface AntColony
{
	/**
	 * Creates and adds an ant to the colony.
	 * 
	 * @return True if the operation succeeded, false otherwise.
	 */
	public boolean addAnt();

	/**
	 * Adds the specified ant to the colony.
	 * 
	 * @param ant - The ant to be added.
	 * @return True if the operation succeeded, false otherwise.
	 */
	public boolean addAnt(Ant ant);

	/**
	 * Kills the colony of ants.
	 */
	public void killColony();

	/**
	 * Gets all the ants.
	 * 
	 * @return An iterator with all the ants in the colony.
	 */
	public Iterator<Ant> getAnts();

	/**
	 * Adds a cycle to the collection of cycles already found.
	 * 
	 * @param cycle - the new cycle to be added.
	 * @return An iterator with the events triggered by completing the specified
	 *         cycle.
	 */
	public Iterator<Event> addCycle(AntCycle cycle);

	/**
	 * Clears the cycles.
	 */
	public void clearCycles();

	/**
	 * Gets the cycles found in the path.
	 * 
	 * @return An iterator with all the cycles already found.
	 */
	public Iterator<AntCycle> getCycles();

	/**
	 * Evaporates the pheromones in the edge.
	 * 
	 * @param edge - The edge that will get his pheromones evaporated.
	 * @return an iterator with subsequent events.
	 */
	public Iterator<Event> evaporatePheromones(WeightedEdge<Integer> edge);

	/**
	 * Gets the nest node.
	 * 
	 * @return the nest node.
	 */
	public Node<Integer> getNest();

	/**
	 * Gets a relative parameter to the move event.
	 * 
	 * @return Alpha - input parameter.
	 */
	public double getAlphaMoveEvent();

	/**
	 * Gets a relative parameter to the move event.
	 * 
	 * @return Beta - input parameter.
	 */
	public double getBetaMoveEvent();

	/**
	 * Gets a parameter related to the move event.
	 * 
	 * @return The Delta input parameter.
	 */
	public double getDeltaMoveEvent();

	/**
	 * Gets a parameter related to the pheromone event.
	 * 
	 * @return The Eta input parameter.
	 */
	public double getPheromoneEventEta();

	/**
	 * Gets a parameter related to the pheromone event.
	 * 
	 * @return The Ro input parameter
	 */
	public double getPheromoneEventRo();

	/**
	 * Gets the pheromone level.
	 * 
	 * @return The Lambda input parameter.
	 */
	public double getPheromoneLevel();

	/**
	 * Gets the total weight of the graph.
	 * 
	 * @return the total weight in the graph.
	 */
	public int getGraphWeight();

	/**
	 * Gets the number of nodes in the graph.
	 * 
	 * @return the number of nodes in the graph.
	 */
	public int getGraphSize();
}
