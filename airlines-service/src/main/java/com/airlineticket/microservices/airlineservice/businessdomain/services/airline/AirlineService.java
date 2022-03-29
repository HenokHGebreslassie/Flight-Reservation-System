package com.airlineticket.microservices.airlineservice.businessdomain.services.airline;

import com.airlineticket.microservices.airlineservice.businessdomain.domains.Airline;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.airline.AirlineAlreadyExistsException;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.airline.AirlineNotFoundException;
import com.airlineticket.microservices.airlineservice.businessdomain.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AirlineService implements IAirlineService{

    @Autowired
    IAirlineDao airlineDao;


    @Override
    public List<Airline> getAll() {
        return airlineDao.findAll();
    }

    @Override
    public Airline getOne(Long id) {
        Optional<Airline> optionalAirline = airlineDao.findById(id);
        if(!optionalAirline.isPresent()){
            throw new AirlineNotFoundException(String.format("Airline with id %d was not found ==>", id));
        }
        return optionalAirline.get();
    }

    @Override
    public Airline save(Airline airline) {
        if(airlineDao.existsAirlineByName(airline.getName())) {
            throw new AirlineAlreadyExistsException(String.format("Airline with name ==> %s already exists",airline.getName()));
        }
        return airlineDao.save(airline);
    }

    @Override
    public Airline update(Long id, Airline airline) {
        Optional<Airline> optionalAirline = airlineDao.findById(id);
        if(!optionalAirline.isPresent()) {
            throw new AirlineNotFoundException("Airline with id ==>" + id + " was not found");
        }

        Airline foundAirline = optionalAirline.get();
        foundAirline.setName(airline.getName());
        return foundAirline;


    }

    @Override
    public void delete(Long id) {
        if(airlineDao.existsById(id)) {
            airlineDao.deleteById(id);
        }
        else{
            throw new AirlineNotFoundException("Airline with id ==>" + id + " was not found" );
        }
    }
}
