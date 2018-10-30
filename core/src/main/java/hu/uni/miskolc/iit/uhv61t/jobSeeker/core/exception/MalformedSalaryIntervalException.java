package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception;

/**
 * Represents that the offered salary interval is wrong.
 */
public class MalformedSalaryIntervalException extends Throwable {
    public MalformedSalaryIntervalException() {
    }

    public MalformedSalaryIntervalException(String message) {
        super(message);
    }

    public MalformedSalaryIntervalException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedSalaryIntervalException(Throwable cause) {
        super(cause);
    }

    public MalformedSalaryIntervalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
