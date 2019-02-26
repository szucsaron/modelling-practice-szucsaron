package com.codecool.place;

public class NotEnoughSpaceException extends Exception {
    public NotEnoughSpaceException(String message) {
        super(message);
    }
    public NotEnoughSpaceException() {

    }
}
