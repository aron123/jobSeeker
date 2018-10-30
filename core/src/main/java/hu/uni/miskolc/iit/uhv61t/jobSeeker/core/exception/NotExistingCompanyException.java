package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception;

/**
 * Represents that the given company is not in the database.
 */
public class NotExistingCompanyException extends Exception {
    public NotExistingCompanyException() {
    }

    public NotExistingCompanyException(String message) {
        super(message);
    }

    public NotExistingCompanyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistingCompanyException(Throwable cause) {
        super(cause);
    }

    public NotExistingCompanyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
