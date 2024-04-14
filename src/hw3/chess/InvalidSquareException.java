package hw3.chess;

/**
 * @author Christoforos Kontzias
 * 
 * represents an exception for invalid square references in a chess board.
 * It is an unchecked exception.
 * I did this because it encourages better programming practises for simpler client code by not forcing the exception to be declared or caught.
 * suitable for errors that the application cannot recover from or when the error is due to a bug
 */
public class InvalidSquareException extends RuntimeException {
    /**
     * constructs an InvalidSquareException with the specified detail message.
     * The detail message provides a more detailed description of the error.
     *
     * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public InvalidSquareException(String message) {
        super(message);
    }
}