package graph.ex;

/**
 * Represents an invalid edge.
 */
public class InvalidEdgeException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * The constructor.
	 */
	public InvalidEdgeException()
	{
		super("The provided edge is invalid!");
	}

	/**
	 * The constructor.
	 * 
	 * @param message The message that will be shown if the exception is thrown.
	 */
	public InvalidEdgeException(String message)
	{
		super(message);
	}
}
