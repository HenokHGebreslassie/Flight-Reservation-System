package com.airlineticket.microservices.airlineservice.businessdomain.services.plane;

import com.airlineticket.microservices.airlineservice.businessdomain.domains.Airline;
import com.airlineticket.microservices.airlineservice.businessdomain.domains.Airplane;
import com.airlineticket.microservices.airlineservice.businessdomain.services.airline.IAirlineService;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.airline.AirlineNotFoundException;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.plane.PlaneNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.airlineticket.microservices.airlineservice.businessdomain.repositories.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PlaneService implements IPlaneService{
    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @Autowired
    private IPlaneDao planeDao;
    @Autowired
    private IAirlineService airlineService;

    @Override
    public List<Airplane> getAll(Long airlineId) throws AirlineNotFoundException {
        Airline airline = airlineService.getOne(airlineId);
        return planeDao.findAllByAirline_Id(airlineId);
    }

    @Override
    public Airplane getOne( Long id) {
        Optional<Airplane> optionalAirplane = planeDao.findById(id);
        if(!optionalAirplane.isPresent()){
            throw new PlaneNotFoundException(String.format("The plane with specified id ==> %d was not found", id));
        }
        return optionalAirplane.get();
    }

    @Override
    public Airplane save(Long airlineId, Airplane airplane) throws AirlineNotFoundException {
        Airline airline = airlineService.getOne(airlineId);
        airplane.setAirline(airline);
        return planeDao.save(airplane);
    }

    @Override
    public Airplane update( Long id, Airplane airplane) {
        Optional<Airplane> optionalAirplane = planeDao.findById(id);
        if(!optionalAirplane.isPresent()) {
            throw new PlaneNotFoundException(String.format("The plane with specified id ==> %d was not found", id));
        }
        Airplane foundAirplane = optionalAirplane.get();
        foundAirplane.setSerialNumber(airplane.getSerialNumber());
        foundAirplane.setPlaneModel(airplane.getPlaneModel());
        return foundAirplane;
    }

    @Override
    public void delete(Long id) {
        planeDao.deleteById(id);
    }

    @Override
    public void saveAll(Long id, int quantity, Airplane airplane) throws AirlineNotFoundException{
        Airline airline = airlineService.getOne(id);
        List<Airplane> airplanes = new ArrayList<>();
        for(int i = 0; i < quantity; i++) {
            Airplane createdPlane = new Airplane();
            createdPlane.setPlaneModel(airplane.getPlaneModel());
            createdPlane.setAirline(airline);
            createdPlane.setSerialNumber(airplane.getSerialNumber());
            createdPlane.setSeats(airplane.getSeats());

            airplanes.add(createdPlane);
            if(i % batchSize == 0 && i > 0) {
                planeDao.saveAll(airplanes);
                airplanes.clear();
            }
        }
        if(airplanes.size() > 0) {
            planeDao.saveAll(airplanes);
            airplanes.clear();
        }
    }
}
