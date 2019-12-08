package by.siarhei.ferry.exception;

public class InvalidInputFilePathException extends Exception {
    public InvalidInputFilePathException() {
    }

    public InvalidInputFilePathException(String message) {
        super(message);
    }

    public InvalidInputFilePathException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputFilePathException(Throwable cause) {
        super(cause);
    }
}
