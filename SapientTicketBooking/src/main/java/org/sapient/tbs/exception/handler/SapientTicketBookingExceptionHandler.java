package org.sapient.tbs.exception.handler;

import org.sapient.tbs.exception.EntityNotFoundException;
import org.sapient.tbs.exception.SeatNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SapientTicketBookingExceptionHandler {
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<?> exception(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getEntityName(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SeatNotAvailableException.class)
    public ResponseEntity<?> exception(SeatNotAvailableException e) {
        return new ResponseEntity<>("Sorry, Requested Seats are not available Please try again", HttpStatus.OK);
    }
}
