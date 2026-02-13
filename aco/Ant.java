package aco;

import java.util.*;

import aco.ex.*;
import sim.*;

/**
 * Represents an Ant.
 */
public interface Ant
{
	/**
	 * Gets the next move events.
	 * 
	 * @return the next events that the Ant will do 
	 * 	(1 move event and 1 evaporation event for each edge in the path if an Hamiltonian cycle is completed) .
	 * @throws InvalidPathException if the next event is invalid
	 */
	public Iterator<Event> getNextEvents() throws InvalidPathException;

	/**
	 * Makes the Ant move to the next node.
	 * 
	 * @throws InvalidPathException if the next move in the path is invalid.
	 */
	public void move() throws InvalidPathException;
}
