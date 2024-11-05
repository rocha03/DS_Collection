package Exceptions;

/**
 * Exception that is thrown when an element is not found.
 */
public class ElementNotFoundException extends Exception {
    /**
     * Default constructor for the ElementNotFoundException.
     */
    public ElementNotFoundException() {
    }

    /**
     *  Constructor for the ElementNotFoundException with a message.
     * 
     * @param message Message that describes the exception.
     */
    public ElementNotFoundException(String message) {
        super(message);
    }
}
