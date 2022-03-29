package com.airlineticket.microservices.airportservice.businessdomain.exceptions.airport;

public class AirportAlreadyExistsException extends RuntimeException {
    public AirportAlreadyExistsException() {
        super();
    }

    public AirportAlreadyExistsException(String message) {
        super(message);
    }

    public AirportAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AirportAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected AirportAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
