package sim;

import java.util.*;

import aco.ex.InvalidPathException;

/**
 * Representation of the PEC
 */
public class PendingEventContainer implements EventContainer
{
	private List<Event> events; // The pending events.
	private List<MoveEvent> moveEvents; // The triggered move events.
	private List<EvaporationEvent> evapEvents; // The triggered evaporation events.

	/**
	 * The constructor.
	 */
	public PendingEventContainer()
	{
		events = new LinkedList<>();
		moveEvents = new LinkedList<>();
		evapEvents = new LinkedList<>();
	}

	@Override
	public void addEvent(Event event)
	{
		events.add(event);
		Collections.sort(events);
	}

	@Override
	public Iterator<Event> simulateEvent(Event event) throws InvalidPathException
	{
		Iterator<Event> nextEvts = event.simulateEvent();

		if (event instanceof MoveEvent)
			moveEvents.add((MoveEvent) event);
		else if (event instanceof EvaporationEvent)
			evapEvents.add((EvaporationEvent) event);

		return nextEvts;
	}

	@Override
	public Event getNextEvent()
	{
		if (events.isEmpty())
			return null;
		return events.get(0);
	}

	@Override
	public Event removeNextEvent()
	{
		if (events.isEmpty())
			return null;
		return events.remove(0);
	}

	@Override
	public int getMoveEventsNumber()
	{
		return moveEvents.size();
	}

	@Override
	public Iterator<MoveEvent> getMoveEvents()
	{

		return moveEvents.iterator();
	}

	@Override
	public int getEvapEventsNumber()
	{
		return evapEvents.size();
	}

	@Override
	public Iterator<EvaporationEvent> getEvapEvents()
	{
		return evapEvents.iterator();
	}
}
