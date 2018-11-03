package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception;

/**
 * Represents error when manipulating the database.
 * Used as technology-independent exception type (e.g. instead of SQLException or XmlMarshalException).
 */
public class PersistenceException extends Exception {
    public PersistenceException() {
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }

    public PersistenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
