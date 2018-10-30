package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception;

/**
 * Represents that the applicant is not in the database.
 */
public class NotExistingApplicantException extends Exception {
    public NotExistingApplicantException() {
    }

    public NotExistingApplicantException(String message) {
        super(message);
    }

    public NotExistingApplicantException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistingApplicantException(Throwable cause) {
        super(cause);
    }

    public NotExistingApplicantException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
