package com.airlineticket.microservices.airportservice.businessdomain.exceptions.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponseDto {
    private Date date;
    private String message;
    private String details;

}
