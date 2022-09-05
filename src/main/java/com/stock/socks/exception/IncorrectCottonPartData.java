package com.stock.socks.exception;

public class IncorrectCottonPartData extends RuntimeException{

    private final static String EXCEPTION_MESSAGE = "Процентное соотношения хлопка должно быть в интервале от 0 до 100";

    public IncorrectCottonPartData() {
        super(EXCEPTION_MESSAGE);
    }
}
