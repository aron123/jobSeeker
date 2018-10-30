package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception;

/**
 * Represents that the salary demand of applicant is invalid.
 */
public class InvalidSalaryDemandException extends Throwable {
    public InvalidSalaryDemandException() {
    }

    public InvalidSalaryDemandException(String message) {
        super(message);
    }

    public InvalidSalaryDemandException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSalaryDemandException(Throwable cause) {
        super(cause);
    }

    public InvalidSalaryDemandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
