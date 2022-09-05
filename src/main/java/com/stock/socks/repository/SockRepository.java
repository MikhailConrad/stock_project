package com.stock.socks.repository;

import com.stock.socks.entity.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SockRepository extends JpaRepository<Sock, Integer> {

    Optional<Sock> findSockByColorAndCottonPart(String color, int cottonPart);

    List<Sock> findAllByColorAndCottonPartGreaterThan(String color, int cottonPart);

    List<Sock> findAllByColorAndCottonPartLessThan(String color, int cottonPart);

    List<Sock> findAllByColorAndCottonPartEquals(String color, int cottonPart);

}
