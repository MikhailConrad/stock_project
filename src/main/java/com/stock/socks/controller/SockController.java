package com.stock.socks.controller;

import com.stock.socks.entity.Sock;
import com.stock.socks.entity.StockIncorrectData;
import com.stock.socks.exception.*;
import com.stock.socks.service.SockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class SockController {

    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }


    @GetMapping
    public int findSocksByColorAndCottonPart(@RequestParam String color,
                                             @RequestParam String operation,
                                             @RequestParam int cottonPart) {

        return sockService.findSockByParameters(color, operation, cottonPart);
    }

    @PostMapping("income")
    public ResponseEntity<String> income(@RequestBody Sock sock) {

        sockService.receiptOfSock(sock);
        return new ResponseEntity<>("Партия носков была принята на склад", HttpStatus.OK);
    }

    @PostMapping("outcome")
    public ResponseEntity<String> outcome(@RequestBody Sock sock) {

        sockService.issuanceOfSock(sock);
        return new ResponseEntity<>("Партия носков была отпущена со склада", HttpStatus.OK);
    }

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

    @ExceptionHandler
    public ResponseEntity<StockIncorrectData> sockNotFound(SockNotFound exception) {

        StockIncorrectData stockIncorrectData = new StockIncorrectData(exception.getMessage());
        return new ResponseEntity<>(stockIncorrectData, HttpStatus.valueOf(400));
    }

    @ExceptionHandler
    public ResponseEntity<Exception> otherException(Exception exception) {

        return new ResponseEntity<>(exception, HttpStatus.valueOf(500));
    }
}
