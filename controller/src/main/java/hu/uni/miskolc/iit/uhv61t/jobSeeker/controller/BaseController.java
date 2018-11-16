package hu.uni.miskolc.iit.uhv61t.jobSeeker.controller;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.controller.dto.ErrorResponse;
import hu.uni.miskolc.iit.uhv61t.jobSeeker.core.exception.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class contains common exception handlers and methods for controllers that extends it.
 */
@RestController
public class BaseController {
    /**
     * Handles PersistenceException.
     */
    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ErrorResponse> handlePersistenceException () {
        return getErrorResponseEntity("Unable to persist data", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles input parsing errors.
     */
    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ErrorResponse> handleMismatchedInputException () {
        return getErrorResponseEntity("Unable to parse input (JSON is expected)", HttpStatus.BAD_REQUEST);
    }

    /**
     * Returns a new ResponseEntity of ErrorResponse with the given message and HTTP status code.
     * @param message The error message.
     * @param httpStatus HTTP status of the error.
     */
    ResponseEntity<ErrorResponse> getErrorResponseEntity (String message, HttpStatus httpStatus) {
        ErrorResponse response = new ErrorResponse();
        response.setSuccess(false);
        response.setError(message);
        return new ResponseEntity<>(response, httpStatus);
    }
}
