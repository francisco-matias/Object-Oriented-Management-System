package graph.ex;

/**
 * Represents an invalid weight.
 */
public class InvalidWeightException extends Exception
{
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor.
	 */
	public InvalidWeightException()
	{
		super("The provided weight is invalid!");
	}
	
	/**
	 * The constructor.
	 * 
	 * @param message The message that will be shown if the exception is thrown.
	 */
	public InvalidWeightException(String message)
	{
		super(message);
	}
}
