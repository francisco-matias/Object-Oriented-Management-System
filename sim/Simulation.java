package sim;

import java.util.*;

import aco.*;
import aco.ex.*;
import graph.*;

/**
 * Represents the simulation.
 */
public interface Simulation
{
	/**
	 * Sets the move events for all the ants in the colony.
	 * 
	 * @throws InvalidPathException if the ant when moving finds an invalid path.
	 */
	public void init() throws InvalidPathException;

	/**
	 * Runs an iteration of the simulation.
	 * 
	 * @throws InvalidPathException if the iteration has an invalid path.
	 */
	public void run() throws InvalidPathException;

	/**
	 * Gets the top cycles (not including the best Hamiltonian cycle).
	 * 
	 * @param topNum the number of top cycles we want to find
	 * @return the top cycles without including the best Hamiltonian one.
	 */
	public Iterator<AntCycle> getTopCycles(int topNum);

	/**
	 * Gets the best cycle, that is the first position of the list of cycles.
	 * 
	 * @return the path with the best Hamiltonian cycle.
	 */
	public AntCycle getBestCycle();

	/**
	 * Gets the graph.
	 * 
	 * @return the graph.
	 */
	public WeightedGraph<Integer, Node<Integer>, WeightedEdge<Integer>> getGraph();

	/**
	 * Gets the PEC.
	 * 
	 * @return the PEC.
	 */
	public EventContainer getPEC();
}
