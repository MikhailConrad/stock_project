package com.stock.socks.exception;

public class SockNotFound extends RuntimeException {

    private final static String EXCEPTION_MESSAGE = "Запрошенных носков нет на складе";

    public SockNotFound() {
        super(EXCEPTION_MESSAGE);
    }

}
