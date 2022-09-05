package com.stock.socks.exception;

public class IncorrectQuantityData extends RuntimeException {

    public IncorrectQuantityData() {
    }

    public IncorrectQuantityData(String message) {
        super(message);
    }
}
