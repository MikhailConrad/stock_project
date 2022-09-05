package com.stock.socks.service;

import com.stock.socks.entity.Sock;
import com.stock.socks.exception.IncorrectCottonPartData;
import com.stock.socks.exception.IncorrectOperation;
import com.stock.socks.exception.IncorrectQuantityData;
import com.stock.socks.exception.SockNotFound;
import com.stock.socks.repository.SockRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SockService {

    private SockRepository sockRepository;

    public SockService(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    public List<Sock> getAllSocks() {

        return sockRepository.findAll();
    }

    public int findSockByParameters(String color, String operation, int cottonPart) {


        if(operation.equals("equal")) {
            return findSumOfSocksQuantity(sockRepository.findAllByColorAndCottonPartEquals(color, cottonPart));

        } else if (operation.equals("lessThan")) {
            return findSumOfSocksQuantity(sockRepository.findAllByColorAndCottonPartLessThan(color, cottonPart));

        } else if (operation.equals("moreThan")) {
            return findSumOfSocksQuantity(sockRepository.findAllByColorAndCottonPartGreaterThan(color, cottonPart));

        } else {
            throw new IncorrectOperation("Неверная операция с данными");
        }
    }

    public int findSumOfSocksQuantity(List<Sock> socks) {
        int sum = 0;
        for (Sock sock: socks) {
            sum += sock.getQuantity();
        }
        return sum;
    }

    public void receiptOfSock(Sock sock) {

        if (sock.getCottonPart() > 100 || sock.getCottonPart() < 0) {
            throw new IncorrectCottonPartData("Процентное соотношения хлопка должно быть в интервале от 0 до 100");
        }
        if(sock.getQuantity() <= 0) {
            throw new IncorrectQuantityData("Количество поступивших пар не может иметь отрицательное значение");
        }

        sockRepository.findSockByColorAndCottonPart(sock.getColor(), sock.getCottonPart())
                .ifPresentOrElse(
                        s -> increaseNumberOfSock(s, sock.getQuantity()),
                        () -> sockRepository.save(sock)
                );
    }

    public void issuanceOfSock(Sock sock) {

        sockRepository.findSockByColorAndCottonPart(sock.getColor(), sock.getCottonPart())
                .ifPresentOrElse(
                        s -> decreaseNumberOfSock(s, sock.getQuantity()),
                        () -> { throw new SockNotFound("Запрошенных носков нет на складе"); }
                );
    }

    public void increaseNumberOfSock(Sock sock, int quantity) {

        int newQuantity = sock.getQuantity() + quantity;
        sock.setQuantity(newQuantity);
        sockRepository.save(sock);
    }

    public void decreaseNumberOfSock(Sock sock, int quantity) {

        int newQuantity = sock.getQuantity() - quantity;
        if(newQuantity >= 0) {
            sock.setQuantity(newQuantity);
            sockRepository.save(sock);
        } else {
            throw new IncorrectQuantityData("Недостаточно пар на складе");
        }
    }
}
