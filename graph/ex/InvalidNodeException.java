package graph.ex;

/**
 * Represents an invalid node.
 */
public class InvalidNodeException extends Exception
{
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor.
	 */
	public InvalidNodeException()
	{
		super("The provided node is invalid!");
	}

	/**
	 * The constructor.
	 * 
	 * @param message The message that will be shown if the exception is thrown.
	 */
	public InvalidNodeException(String message)
	{
		super(message);
	}
}
