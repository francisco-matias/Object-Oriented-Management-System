package aco;

import graph.*;

/**
 * Represents an incomplete Hamiltonian Cycle.
 */
public interface AntPath extends AntCycle
{
	/**
	 * 
	 * Adds a node to the path.
	 * 
	 * @param node - The node that will be added to the path.
	 */
	public void addToPath(Node<Integer> node);
}
