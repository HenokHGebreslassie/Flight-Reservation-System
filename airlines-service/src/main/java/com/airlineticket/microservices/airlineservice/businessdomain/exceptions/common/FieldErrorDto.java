package com.airlineticket.microservices.airlineservice.businessdomain.exceptions.common;

public class FieldErrorDto {
    private String field;
    private String message;

    public FieldErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FieldErrorDto{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
