package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception;

/**
 * Represents that the job is not exists in the database.
 */
public class NotExistingJobException extends Exception {
    public NotExistingJobException() {
    }

    public NotExistingJobException(String message) {
        super(message);
    }

    public NotExistingJobException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistingJobException(Throwable cause) {
        super(cause);
    }

    public NotExistingJobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
