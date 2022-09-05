package com.stock.socks.exception;

public class SockNotFound extends RuntimeException {

    public SockNotFound() {
    }

    public SockNotFound(String message) {
        super(message);
    }
}
