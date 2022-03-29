package com.airlineticket.microservices.airlineservice.businessdomain.repositories;

import com.airlineticket.microservices.airlineservice.businessdomain.domains.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(propagation = Propagation.MANDATORY)
public interface ISeatDao extends JpaRepository<Seat, Long> {
    List<Seat> findAllByAirplane_IdAndReservedEquals(Long airplaneId, boolean reserved);
    List<Seat> findAllByAirplane_Id(Long airplaneId);
}
