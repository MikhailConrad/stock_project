package com.stock.socks.exception;

public class IncorrectOperation extends RuntimeException{

    private final static String EXCEPTION_MESSAGE = "Неверная операция с данными";

    public IncorrectOperation() {
        super(EXCEPTION_MESSAGE);
    }
}
