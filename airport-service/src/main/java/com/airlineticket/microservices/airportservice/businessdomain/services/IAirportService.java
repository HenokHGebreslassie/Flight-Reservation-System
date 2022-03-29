package com.airlineticket.microservices.airportservice.businessdomain.services;

import com.airlineticket.microservices.airportservice.businessdomain.domains.Airport;

import java.util.List;

public interface IAirportService {
    List<Airport> getAll();
    Airport getOne(Long id);
    Airport getByCode(String code);
    Airport save(Airport airport);
    Airport update(Airport airport);
    void delete(Long id);

}
