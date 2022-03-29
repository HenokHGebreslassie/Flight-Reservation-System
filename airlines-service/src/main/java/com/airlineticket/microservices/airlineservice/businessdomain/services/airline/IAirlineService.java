package com.airlineticket.microservices.airlineservice.businessdomain.services.airline;

import com.airlineticket.microservices.airlineservice.businessdomain.domains.Airline;

import java.util.List;

public interface IAirlineService {
    List<Airline> getAll();
    Airline getOne(Long id);
    Airline save(Airline airline);
    Airline update(Long id, Airline airline);
    void delete(Long id);
}
