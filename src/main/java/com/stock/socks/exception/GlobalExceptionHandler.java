package com.stock.socks.exception;

import com.stock.socks.entity.StockIncorrectData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IncorrectOperation.class)
    public ResponseEntity<StockIncorrectData> incorrectOperation(IncorrectOperation exception) {

        StockIncorrectData stockIncorrectData = new StockIncorrectData(exception.getMessage());
        return new ResponseEntity<>(stockIncorrectData, HttpStatus.valueOf(400));
    }

    @ExceptionHandler(IncorrectQuantityData.class)
    public ResponseEntity<StockIncorrectData> incorrectQuantityData(IncorrectQuantityData exception) {

        StockIncorrectData stockIncorrectData = new StockIncorrectData(exception.getMessage());
        return new ResponseEntity<>(stockIncorrectData, HttpStatus.valueOf(400));
    }

    @ExceptionHandler(IncorrectCottonPartData.class)
    public ResponseEntity<StockIncorrectData> incorrectCottonPartData(IncorrectCottonPartData exception) {

        StockIncorrectData stockIncorrectData = new StockIncorrectData(exception.getMessage());
        return new ResponseEntity<>(stockIncorrectData, HttpStatus.valueOf(400));
    }

    @ExceptionHandler(SockNotFound.class)
    public ResponseEntity<StockIncorrectData> sockNotFound(SockNotFound exception) {

        StockIncorrectData stockIncorrectData = new StockIncorrectData(exception.getMessage());
        return new ResponseEntity<>(stockIncorrectData, HttpStatus.valueOf(400));
    }

    @ExceptionHandler
    public ResponseEntity<Exception> otherException(Exception exception) {

        return new ResponseEntity<>(exception, HttpStatus.valueOf(500));
    }
}
