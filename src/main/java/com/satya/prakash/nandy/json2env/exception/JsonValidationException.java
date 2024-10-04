package com.satya.prakash.nandy.json2env.exception;

public class JsonValidationException extends RuntimeException {
    private String message;
    public JsonValidationException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
