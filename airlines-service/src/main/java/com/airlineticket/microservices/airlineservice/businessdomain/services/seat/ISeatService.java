package com.airlineticket.microservices.airlineservice.businessdomain.services.seat;

import com.airlineticket.microservices.airlineservice.businessdomain.domains.Seat;

import java.util.List;

public interface ISeatService {
    List<Seat> getAllUnreserved(Long planeId);
    List<Seat> getAll(Long planeId);
    Seat getOne(Long id);
    void saveAll(Long planeId, int quantity, Seat seat);
    Seat update(Long seatId, Seat seat);
    void delete(Long id);
}
