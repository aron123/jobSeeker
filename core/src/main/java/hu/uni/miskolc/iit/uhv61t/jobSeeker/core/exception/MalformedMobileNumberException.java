package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception;

public class MalformedMobileNumberException extends Throwable {
    public MalformedMobileNumberException() {
    }

    public MalformedMobileNumberException(String message) {
        super(message);
    }

    public MalformedMobileNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedMobileNumberException(Throwable cause) {
        super(cause);
    }

    public MalformedMobileNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
