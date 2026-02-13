package sim;

import java.util.*;

/**
 * Represents an abstract event.
 */
public abstract class EventClass implements Event
{
	// The date on which the event will be simulated.
	private Date date;

	protected EventClass(Date date)
	{
		this.date = date;
	}

	@Override
	public Date getTime()
	{
		return date;
	}

	@Override
	public boolean hasHappened()
	{
		return date.before(new Date());
	}

	@Override
	public int compareTo(Event o)
	{
		return date.compareTo(o.getTime());
	}
}
