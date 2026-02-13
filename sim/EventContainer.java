package sim;

import java.util.*;

import aco.ex.*;

/**
 * Representation of the PEC
 */
public interface EventContainer
{
	/**
	 * Adds an event to the PEC.
	 * 
	 * @param event The event that will be added to the PEC.
	 */
	public void addEvent(Event event);

	/**
	 * Simulates the specified event.
	 * 
	 * @param event The event to be simulated.
	 * @return an iterator with the subsequent events.
	 * @throws InvalidPathException if the path while simulating is invalid.
	 */
	public Iterator<Event> simulateEvent(Event event) throws InvalidPathException;

	/**
	 * Gets the next event to be added to the PEC.
	 * 
	 * @return an event to add to the PEC.
	 */
	public Event getNextEvent();

	/**
	 * Removes the next event to simulate.
	 * 
	 * @return The removed event.
	 */
	public Event removeNextEvent();

	/**
	 * Gets all the move events.
	 * 
	 * @return the number of move events.
	 */
	public int getMoveEventsNumber();

	/**
	 * Gets all move events.
	 * 
	 * @return an iterator with all the move events.
	 */
	public Iterator<MoveEvent> getMoveEvents();

	/**
	 * Gets the total number of evaporations number.
	 * 
	 * @return the total number of evaporation events.
	 */
	public int getEvapEventsNumber();

	/**
	 * Gets all the evaporation events.
	 * 
	 * @return an iterator with all the evaporation events.
	 */
	public Iterator<EvaporationEvent> getEvapEvents();
}
