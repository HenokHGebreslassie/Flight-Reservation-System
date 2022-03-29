package com.airlineticket.microservices.airlineservice.businessdomain.exceptions.common;

import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.airline.AirlineAlreadyExistsException;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.airline.AirlineNotFoundException;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.plane.PlaneAlreadyExistsException;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.plane.PlaneNotFoundException;
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
//Todo - refactor to using generic entity not found exception and entity exists exception
// using generics
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(AirlineNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponseDto handleAirlineNotFoundException(AirlineNotFoundException ex, WebRequest request) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(new Date(), ex.getMessage(), request.getDescription(false));
        return exceptionResponseDto;
    }
    @ExceptionHandler(AirlineAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResponseDto handleAirlineExistsException(AirlineAlreadyExistsException ex, WebRequest request) {
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
    @ExceptionHandler(PlaneNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponseDto handlePlaneNotFoundException(PlaneNotFoundException ex, WebRequest request) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(new Date(), ex.getMessage(), request.getDescription(false));
        return exceptionResponseDto;
    }
    @ExceptionHandler(PlaneAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResponseDto handlePlaneExistsException(PlaneAlreadyExistsException ex, WebRequest request) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(new Date(), ex.getMessage(), request.getDescription(false));
        return exceptionResponseDto;
    }

}
