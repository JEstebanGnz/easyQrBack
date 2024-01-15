package easyqr.unir.easyqr.Exceptions;

import org.hibernate.validator.internal.constraintvalidators.hv.ru.INNValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {InvalidTokenException.class})

    public ResponseEntity<APIResponse> invalidTokenException(InvalidTokenException ex) {
        APIResponse response = new APIResponse(true, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<APIResponse> resourceNotFoundException(ResourceNotFoundException ex) {
        APIResponse response = new APIResponse(true, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
