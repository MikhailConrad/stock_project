package com.stock.socks.controller;

import com.stock.socks.model.entity.Sock;
import com.stock.socks.repository.SockRepository;
import com.stock.socks.service.SockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;


@SpringBootTest(classes = {SockController.class, SockService.class})
class SockControllerTest {

    @Autowired
    private SockController sockController;

    @MockBean
    private SockRepository sockRepository;

    @Test
    void findSocksByColorAndCottonPart() {
        //given
        Mockito.when(sockRepository.findAllByColorAndCottonPartLessThan(anyString(), anyInt()))
                .thenReturn(List.of(
                        new Sock("red", 40, 50),
                        new Sock("red", 50, 50),
                        new Sock("red", 50, 25))
                );

        //when
        int sockByParameters = sockController.findSocksByColorAndCottonPart("red", "lessThan", 60);

        //then
        Assertions.assertEquals(125, sockByParameters);
    }
}