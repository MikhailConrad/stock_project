package com.stock.socks.exception;

public class IncorrectOperation extends RuntimeException{

    public IncorrectOperation() {
    }

    public IncorrectOperation(String message) {
        super(message);
    }
}
