package com.epam.esm.handler;

import com.epam.esm.dto.ErrorDto;
import com.epam.esm.exception.IncorrectParameterValueException;
import com.epam.esm.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

/**
 * The type ExceptionProcessor.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionProcessor extends ResponseEntityExceptionHandler {
    private static final int INCORRECT_PARAMETER_VALUE_CODE = 40;
    private static final int RESOURCE_NOT_FOUND_CODE = 44;
    private final ExceptionMessageCreator exceptionMessageCreator;

    @Autowired
    public ExceptionProcessor(ExceptionMessageCreator exceptionMessageCreator) {
        this.exceptionMessageCreator = exceptionMessageCreator;
    }
    /**
     * handle Incorrect Parameter Value Exception.
     *
     * @return ErrorDto
     */
    @ExceptionHandler(value = IncorrectParameterValueException.class)
    public ResponseEntity<ErrorDto> handleIncorrectParameterValueException(IncorrectParameterValueException exception,
                                                                           Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(exception.getErrorMessages(), locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, INCORRECT_PARAMETER_VALUE_CODE), HttpStatus.BAD_REQUEST);
    }

    /**
     * handle Resource Not Found Exception.
     *
     * @return ErrorDto
     */
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException exception,  Locale locale) {
        String exceptionMessage = exceptionMessageCreator.createMessage(exception.getMessage(), locale);
        return new ResponseEntity<>(new ErrorDto(exceptionMessage, RESOURCE_NOT_FOUND_CODE), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers, HttpStatus status,
                                                                   WebRequest request) {
        return new ResponseEntity<>(new ErrorDto(ex.getLocalizedMessage(), INCORRECT_PARAMETER_VALUE_CODE),
                new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
