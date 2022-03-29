package com.airlineticket.microservices.airlineservice.businessdomain.exceptions.airline;

public class AirlineNotFoundException extends RuntimeException{
    public AirlineNotFoundException() {
        super();
    }

    public AirlineNotFoundException(String message) {
        super(message);
    }

    public AirlineNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirlineNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AirlineNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
