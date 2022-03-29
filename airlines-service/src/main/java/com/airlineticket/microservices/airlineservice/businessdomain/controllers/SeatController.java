package com.airlineticket.microservices.airlineservice.businessdomain.controllers;

import com.airlineticket.microservices.airlineservice.businessdomain.domains.Seat;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.plane.PlaneNotFoundException;
import com.airlineticket.microservices.airlineservice.businessdomain.services.seat.ISeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class SeatController {

    private ISeatService seatService;

    @GetMapping(value = "/airplanes/{planeId}/seats", headers = {"X-API-VERSION=v1"}, params = "reserved=false")
    public List<Seat> getAllUnreserved(@PathVariable Long planeId) throws PlaneNotFoundException {
        return seatService.getAllUnreserved(planeId);
    }
    @GetMapping(value = "/airplanes/{planeId}/seats", headers = {"X-API-VERSION=v1"})
    public List<Seat> getAll(@PathVariable Long planeId) throws PlaneNotFoundException {
        return seatService.getAll(planeId);
    }
    @PostMapping(value = "/airplanes/{planeId}/seats", headers = {"X-API-VERSION=v1"}, params = "quantity")
    public ResponseEntity<Object> createSeats(@PathVariable Long planeId, @RequestParam int quantity, @Valid @RequestBody Seat seat){
        seatService.saveAll(planeId, quantity, seat);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("").build().toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping(value = "/seats/{seatId}", headers = {"X-API-VERSION=v1"})
    public ResponseEntity<Object> reserveSeat( @PathVariable Long seatId, @Valid @RequestBody Seat seat) throws PlaneNotFoundException {
        System.out.println("deleting seat");
        seatService.update( seatId,  seat);
        return ResponseEntity.noContent().build();
    }
}
