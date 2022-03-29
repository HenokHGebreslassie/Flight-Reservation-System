package com.airlineticket.microservices.airportservice.businessdomain.exceptions.common;

import com.airlineticket.microservices.airportservice.businessdomain.exceptions.airport.AirportAlreadyExistsException;
import com.airlineticket.microservices.airportservice.businessdomain.exceptions.airport.AirportNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(AirportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponseDto handleUserNotFoundException(AirportNotFoundException ex, WebRequest request) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(new Date(), ex.getMessage(), request.getDescription(false));
        return exceptionResponseDto;
    }
    @ExceptionHandler(AirportAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResponseDto handleUserNotFoundException(AirportAlreadyExistsException ex, WebRequest request) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(new Date(), ex.getMessage(), request.getDescription(false));
        return exceptionResponseDto;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        BindingResult result = ex.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();
        return new ResponseEntity(processFieldErrors(errors), HttpStatus.BAD_REQUEST);
    }

    private ValidationErrorDto processFieldErrors(List<FieldError> errors) {
        ValidationErrorDto dto = new ValidationErrorDto(new Date(), "Validation Errors");
        for(FieldError error : errors) {
            dto.addFieldError(new FieldErrorDto(error.getField(), messageSourceAccessor.getMessage(error)));
        }
        return dto;
    }

}
