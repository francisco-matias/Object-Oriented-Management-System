package sim;

import java.util.*;

import aco.*;
import aco.ex.*;
import graph.*;

/**
 * Represents an evaporation event.
 */
public class EvaporationEvent extends EventClass implements Event
{
	private AntColony colony;
	private WeightedEdge<Integer> edge;

	/**
	 * The constructor.
	 * 
	 * @param date Date The moment when the event will be simulated. 
	 * @param colony All the ants presented in the colony.
	 * @param edge The edge where the evaporation event will take place.
	 */
	public EvaporationEvent(Date date, AntColony colony, WeightedEdge<Integer> edge)
	{
		// Calls the constructor of the super class (EventClass)
		super(date);

		this.colony = colony;
		this.edge = edge;
	}

	@Override
	public Iterator<Event> simulateEvent() throws InvalidPathException
	{
		return colony.evaporatePheromones(edge);
	}
}
