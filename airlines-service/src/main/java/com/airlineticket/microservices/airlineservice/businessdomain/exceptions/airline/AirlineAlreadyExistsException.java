package com.airlineticket.microservices.airlineservice.businessdomain.exceptions.airline;

public class AirlineAlreadyExistsException extends RuntimeException{
    public AirlineAlreadyExistsException() {
        super();
    }

    public AirlineAlreadyExistsException(String message) {
        super(message);
    }

    public AirlineAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirlineAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected AirlineAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
