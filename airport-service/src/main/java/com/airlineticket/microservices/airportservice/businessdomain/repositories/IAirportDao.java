package com.airlineticket.microservices.airportservice.businessdomain.repositories;

import com.airlineticket.microservices.airportservice.businessdomain.domains.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface IAirportDao extends JpaRepository<Airport, Long> {
     Airport findAirportByAirportCode(String airportCode);
}
