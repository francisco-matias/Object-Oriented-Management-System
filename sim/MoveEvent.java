package sim;

import java.util.*;

import aco.*;
import aco.ex.*;

/**
 * Represents a move event.
 */
public class MoveEvent extends EventClass implements Event
{
	private Ant ant;
	
	/**
	 * The constructor.
	 * 
	 * @param date The moment when the event will be simulated. 
	 * @param ant The ant that will move.
	 */
	public MoveEvent(Date date, Ant ant)
	{
		// Calls the constructor of the super class (EventClass)
		super(date);

		this.ant = ant;
	}

	@Override
	public Iterator<Event> simulateEvent() throws InvalidPathException
	{
		ant.move();
		return ant.getNextEvents();
	}
}
