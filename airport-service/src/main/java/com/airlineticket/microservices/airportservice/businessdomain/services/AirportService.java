package com.airlineticket.microservices.airportservice.businessdomain.services;

import com.airlineticket.microservices.airportservice.businessdomain.domains.Airport;
import com.airlineticket.microservices.airportservice.businessdomain.repositories.IAirportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AirportService implements IAirportService{

    @Autowired
    IAirportDao airportDao;

    @Override
    public List<Airport> getAll() {
        return airportDao.findAll();
    }

    @Override
    public Airport getOne(Long id) {
        return airportDao.findById(id).get();
    }

    @Override
    public Airport getByCode(String code) {
        return airportDao.findAirportByAirportCode(code);
    }

    @Override
    public Airport save(Airport airport) {
        return airportDao.save(airport);
    }

    @Override
    public void delete(Long id) {
        airportDao.deleteById(id);
    }
    @Override
    public Airport update(Airport airport) {
        return airportDao.save(airport);
    }
}
