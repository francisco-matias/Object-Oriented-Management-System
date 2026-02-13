package aco;

import java.util.*;

import graph.*;

/**
 * Represents a Hamiltonian Cycle traversed by the Ant. 
 */
public interface AntCycle extends Comparable<AntCycle>
{
	/**
	 * Gets the Node Ids of the path.
	 * 
	 * @return The Ids of the nodes, not including the final node (also know as nest
	 *         node - To perform a cycle)
	 */
	public Iterator<Integer> getPathIds();

	/**
	 * Gets the nodes in the path.
	 * 
	 * @return the path.
	 */
	public Iterator<Node<Integer>> getPath();

	/**
	 * Gets the current node.
	 * 
	 * @return the node that is at the top of the stack.
	 */
	public Node<Integer> getCurrent();

	/**
	 * Checks if a path is new.
	 * 
	 * @param nodeId - the id of the node.
	 * @return false if the path already seen, true otherwise.
	 */
	public boolean pathContains(int nodeId);

	/**
	 * Gets the weight of the ant path.
	 * 
	 * @return The accumulated weight of this path.
	 */
	public int getPathWeight();

	/**
	 * Gets the number of nodes that are in the path.
	 * 
	 * @return the number of nodes included in the path.
	 */
	public int getPathSize();
}
