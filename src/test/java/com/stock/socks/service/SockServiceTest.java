package com.stock.socks.service;

import com.stock.socks.model.entity.Sock;
import com.stock.socks.repository.SockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class SockServiceTest {

    @InjectMocks
    private SockService sockService;

    @Mock
    private SockRepository sockRepository;

    @BeforeEach
    void setUp() {
        sockService = new SockService(sockRepository);
        Mockito.when(sockRepository.findAllByColorAndCottonPartEquals(anyString(), anyInt()))
                .thenReturn(Collections.singletonList(new Sock("red", 40, 1)));
    }

    @Test
    public void findSockByParametersPositiveTest() {
        //given
        String color = "red";
        String operation = "equal";
        int cottonPart = 40;

        //when
        int sockByParameters = sockService.findSockByParameters(color, operation, cottonPart);

        //then
        Assertions.assertEquals(1, sockByParameters);
    }
}