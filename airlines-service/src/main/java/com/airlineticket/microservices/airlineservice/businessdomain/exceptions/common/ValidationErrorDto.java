package com.airlineticket.microservices.airlineservice.businessdomain.exceptions.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class ValidationErrorDto {
    private  Date date;
    private  String message;
    private List<FieldErrorDto> fieldErrorDtos = new ArrayList<>();

    public ValidationErrorDto(Date date, String message) {
        this.date = date;
        this.message = message;
    }
    public void addFieldError(FieldErrorDto fieldErrorDto) {
        fieldErrorDtos.add(fieldErrorDto);
    }
}
