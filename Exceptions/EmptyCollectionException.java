package Exceptions;

public class EmptyCollectionException extends Exception {
    public EmptyCollectionException() {
    }

    /**
     * @param message
     */
    public EmptyCollectionException(String message) {
        super(message);
    }
}
