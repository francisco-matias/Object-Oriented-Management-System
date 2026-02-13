package math;

import java.util.*;

/**
 * Represents the distribution used in some formulas.
 */
public class Distribution
{
	/**
	 * Executes the exponential distribution formula.
	 * 
	 * @param m The mean values.
	 * @return the exponential distribution.
	 */
	public static double exponentialDist(double m)
	{
		Random rand = new Random();
		double next = rand.nextDouble();
		return -m * Math.log(1.0 - next);
	}

	/**
	 * Executes the uniform distribution formula.
	 * 
	 * @param y The even probability for all the edges.
	 * @return the uniform distribution.
	 */
	public static double uniformDist(double y)
	{
		Random rand = new Random();
		double number = rand.nextDouble();
		return number * y;
	}

	/**
	 * Calculates a date that occurs the specified number of milliseconds from the
	 * current instant.
	 * 
	 * @param millis The milliseconds.
	 * @return A date.
	 */
	public static Date getDateFromNow(int millis)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, millis);
		return calendar.getTime();
	}
}
