package com.airlineticket.microservices.airportservice.businessdomain.controllers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.airlineticket.microservices.airportservice.businessdomain.domains.Airport;
import com.airlineticket.microservices.airportservice.businessdomain.exceptions.airport.AirportNotFoundException;
import com.airlineticket.microservices.airportservice.businessdomain.services.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class AirportController {

    @Autowired
    private IAirportService airportService;

    @GetMapping(value = "/airports", headers = {"X-API-VERSION=v1"})
    public List<Airport> getAll() {
        System.out.println("I am Here");
        return airportService.getAll();
    }

    @GetMapping(value = "/airports/{id}", headers = {"X-API-VERSION=v1"} )
    public EntityModel<Airport> getAirport(@PathVariable Long id) {
        Airport airport = airportService.getOne(id);
        if(airport == null) {
            throw new AirportNotFoundException(String.format("Airport with id {id} was not found ==>", id));
        }
        EntityModel<Airport> airportEntityModel = EntityModel.of(airport);
        WebMvcLinkBuilder linkToAirports = linkTo(methodOn(this.getClass()).getAll());
        airportEntityModel.add((linkToAirports).withRel("all-airports"));
        return airportEntityModel;
    }
    @GetMapping(value = "/airports",headers = {"X-API-VERSION=v1"}, params ="airportCode")
    public Airport getAirportByCode(@RequestParam String airportCode) {
        Airport airport = airportService.getByCode(airportCode);
        if(airport == null) {
            throw new AirportNotFoundException(String.format("Airport with id {code} wasn't found ==>", airportCode));
        }
        return airport;
    }

    @PostMapping(value = "/airports", headers = {"X-API-VERSION=v1"})
    public ResponseEntity<Object> createAirport(@Valid @RequestBody Airport airport) {
        Airport savedAirport = airportService.save(airport);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAirport.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
    @PutMapping(value = "/airports{id}", headers = {"X-API-VERSION=v1"})
    public ResponseEntity<Object> updateAirport(@Valid @RequestBody Airport airport, @PathVariable Long id) {
        Airport updatedAirport = airportService.update(airport);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/airports/{id}", headers = {"X-API-VERSION=v1"})
    public void deleteAirport(@PathVariable Long id) {
        airportService.delete(id);
    }


}
