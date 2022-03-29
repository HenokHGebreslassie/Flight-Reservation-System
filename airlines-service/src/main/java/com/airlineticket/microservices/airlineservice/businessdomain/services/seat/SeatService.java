package com.airlineticket.microservices.airlineservice.businessdomain.services.seat;

import com.airlineticket.microservices.airlineservice.businessdomain.domains.Airplane;
import com.airlineticket.microservices.airlineservice.businessdomain.domains.Seat;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.Seat.SeatNotFoundException;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.airline.AirlineNotFoundException;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.plane.PlaneNotFoundException;
import com.airlineticket.microservices.airlineservice.businessdomain.repositories.ISeatDao;
import com.airlineticket.microservices.airlineservice.businessdomain.services.plane.IPlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class SeatService implements ISeatService{

    @Autowired
    private ISeatDao seatDao;
    @Autowired
    private IPlaneService planeService;
    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @Override
    public List<Seat> getAllUnreserved(Long planeId) throws PlaneNotFoundException {
        Airplane airplane = planeService.getOne(planeId);
        return seatDao.findAllByAirplane_IdAndReservedEquals(planeId, false);
    }

    @Override
    public List<Seat> getAll(Long planeId) throws PlaneNotFoundException {
        Airplane airplane = planeService.getOne(planeId);
        return seatDao.findAllByAirplane_Id(planeId);
    }

    @Override
    public Seat getOne(Long id) {
        return seatDao.findById(id).get();
    }

    @Override
    public void saveAll(Long planeId, int quantity, Seat seat) {
        Airplane airplane = planeService.getOne(planeId);
        List<Seat> seats = new ArrayList<>();
        int rowStart = seat.getRow();
        for(int i = 0; i < quantity; i++) {
            Seat createdSeat = new Seat();
            createdSeat.setAirplane(airplane);
            createdSeat.setSide(seat.getSide());
            createdSeat.setSeatType(seat.getSeatType());
            createdSeat.setColumn(seat.getColumn());
            createdSeat.setRow(rowStart++);

            seats.add(createdSeat);
            if(i % batchSize == 0 && i > 0) {
                seatDao.saveAll(seats);
                seats.clear();
            }
        }
        if(seats.size() > 0) {
            seatDao.saveAll(seats);
            seats.clear();
        }
    }

    @Override
    public Seat update(Long id, Seat seat) {
        Optional<Seat> optionalSeat = seatDao.findById(id);
        if(!optionalSeat.isPresent()) {
            throw new SeatNotFoundException(String.format("The Seat with id %d at column %d row %d  and side %s was not found",
                    id, seat.getColumn(), seat.getRow(), seat.getSide()));
        }
        else{
            Seat foundSeat = optionalSeat.get();
            foundSeat.setReserved(true);
            return seatDao.save(seat);
        }

    }

    @Override
    public void delete(Long id) {
        seatDao.deleteById(id);
    }


}
