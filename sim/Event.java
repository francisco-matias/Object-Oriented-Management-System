package sim;

import java.util.*;

import aco.ex.*;

/**
 * Represents an evaporation event.
 */
public interface Event extends Comparable<Event>
{
	/**
	 * Moves the ant and get the next move events to the ant.
	 * 
	 * @return the next events of the ant.
	 * @throws InvalidPathException if an invalid path is encountered during the
	 *                              event simulation.
	 */
	public Iterator<Event> simulateEvent() throws InvalidPathException;

	/**
	 * Gets the time of an event.
	 * 
	 * @return the time that the event will eventually occur.
	 */
	public Date getTime();

	/**
	 * Verifies if an event has already happened.
	 * 
	 * @return true if event has already happened, false otherwise.
	 */
	public boolean hasHappened();
}
