package Exceptions;

/**
 * Default constructor for the EmptyCollectionException.
 */
public class EmptyCollectionException extends Exception {
    /**
     * Constructor that accepts an error message.
     */
    public EmptyCollectionException() {
    }

    /**
     * Constructor that accepts an error message.
     * 
     * @param message the error message to be passed to the exception.
     */
    public EmptyCollectionException(String message) {
        super(message);
    }
}
