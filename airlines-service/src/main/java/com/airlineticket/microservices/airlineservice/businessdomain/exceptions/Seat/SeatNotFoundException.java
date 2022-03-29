package com.airlineticket.microservices.airlineservice.businessdomain.exceptions.Seat;

public class SeatNotFoundException extends RuntimeException{
    public SeatNotFoundException() {
        super();
    }

    public SeatNotFoundException(String message) {
        super(message);
    }

    public SeatNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeatNotFoundException(Throwable cause) {
        super(cause);
    }

    protected SeatNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
