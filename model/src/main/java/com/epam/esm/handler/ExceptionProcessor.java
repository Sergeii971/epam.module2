package com.epam.esm.handler;

import com.epam.esm.dto.ErrorDto;
import com.epam.esm.exception.IncorrectParameterValueException;
import com.epam.esm.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type ExceptionProcessor.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionProcessor extends ResponseEntityExceptionHandler {
    /**
     * handle Incorrect Parameter Value Exception.
     *
     * @return ErrorDto
     */
    @ExceptionHandler(value = IncorrectParameterValueException.class)
    public ResponseEntity<ErrorDto> handleIncorrectParameterValueException(IncorrectParameterValueException exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    /**
     * handle Resource Not Found Exception.
     *
     * @return ErrorDto
     */
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(new ErrorDto(exception.getMessage(), 404), HttpStatus.NOT_FOUND);
    }
}
