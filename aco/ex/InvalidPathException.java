package aco.ex;

/**
 * Represents an invalid path.
 */
public class InvalidPathException extends Exception
{
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor.
	 */
	public InvalidPathException()
	{
		super("There is no valid path.");
	}
	
	/**
	 * The constructor.
	 * 
	 * @param message The message that will be shown if the exception is thrown.
	 */
	public InvalidPathException(String message)
	{
		super(message);
	}
}
