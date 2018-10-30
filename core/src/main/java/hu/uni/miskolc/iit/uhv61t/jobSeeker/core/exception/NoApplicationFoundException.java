package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception;

/**
 * Represents that no application found in database with the given conditions.
 */
public class NoApplicationFoundException extends Exception {
    public NoApplicationFoundException() {
    }

    public NoApplicationFoundException(String message) {
        super(message);
    }

    public NoApplicationFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoApplicationFoundException(Throwable cause) {
        super(cause);
    }

    public NoApplicationFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
