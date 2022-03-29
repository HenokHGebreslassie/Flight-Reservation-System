package com.airlineticket.microservices.airlineservice.businessdomain.repositories;

import com.airlineticket.microservices.airlineservice.businessdomain.domains.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface IAirlineDao extends JpaRepository<Airline, Long> {
    boolean existsAirlineByName(String name);
}
