package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception;

/**
 * Represents that a job is currently exists in database.
 */
public class ExistingJobException extends Exception {
    public ExistingJobException() {
    }

    public ExistingJobException(String message) {
        super(message);
    }

    public ExistingJobException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistingJobException(Throwable cause) {
        super(cause);
    }

    public ExistingJobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
