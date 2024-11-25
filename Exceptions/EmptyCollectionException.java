package Exceptions;

/**
 * Custom exception to indicate that an operation was attempted on
 * an empty collection.
 */
public class EmptyCollectionException extends Exception {

    /**
     * Creates a new instance of EmptyCollectionException with a default error message.
     */
    public EmptyCollectionException() {
        super("The collection is empty.");
    }

    /**
     * Creates a new instance of EmptyCollectionException with a custom error message.
     *
     * @param message the error message to be passed to the exception.
     */
    public EmptyCollectionException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of EmptyCollectionException with a custom error message
     * and a cause.
     *
     * @param message the error message to be passed to the exception.
     * @param cause   the cause of this exception.
     */
    public EmptyCollectionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new instance of EmptyCollectionException with a cause.
     *
     * @param cause the cause of this exception.
     */
    public EmptyCollectionException(Throwable cause) {
        super(cause);
    }
}
