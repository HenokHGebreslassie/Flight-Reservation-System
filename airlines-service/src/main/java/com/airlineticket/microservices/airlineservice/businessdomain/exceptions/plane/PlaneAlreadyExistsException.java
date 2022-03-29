package com.airlineticket.microservices.airlineservice.businessdomain.exceptions.plane;

public class PlaneAlreadyExistsException extends RuntimeException {
    public PlaneAlreadyExistsException() {
        super();
    }

    public PlaneAlreadyExistsException(String message) {
        super(message);
    }

    public PlaneAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaneAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected PlaneAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
