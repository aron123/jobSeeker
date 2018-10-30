package hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception;

/**
 * Represents that the given email address is malformed.
 */
public class MalformedEmailAddressException extends Throwable {
    public MalformedEmailAddressException() {
    }

    public MalformedEmailAddressException(String message) {
        super(message);
    }

    public MalformedEmailAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedEmailAddressException(Throwable cause) {
        super(cause);
    }

    public MalformedEmailAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
