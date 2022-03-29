package com.airlineticket.microservices.airlineservice.businessdomain.controllers;


import com.airlineticket.microservices.airlineservice.businessdomain.domains.Airline;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.airline.AirlineAlreadyExistsException;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.airline.AirlineNotFoundException;
import com.airlineticket.microservices.airlineservice.businessdomain.services.airline.IAirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    @Autowired
    private IAirlineService airlineService;

    @GetMapping( headers = {"X-API-VERSION=v1"})
    public List<Airline> getAll() {
        return airlineService.getAll();
    }

    @GetMapping(value = "/{id}", headers = {"X-API-VERSION=v1"} )
    public EntityModel<Airline> getAirline(@PathVariable Long id) throws AirlineNotFoundException {
        Airline airline  = airlineService.getOne(id);
        EntityModel<Airline> airlineEntityModel = EntityModel.of(airline);
        WebMvcLinkBuilder linkToAirlines = linkTo(methodOn(this.getClass()).getAll());
        airlineEntityModel.add((linkToAirlines).withRel("all-airlines"));
        WebMvcLinkBuilder linkToPlanes = linkTo(methodOn(PlaneController.class).getAll(id));
        airlineEntityModel.add((linkToPlanes).withRel("planes"));
        return airlineEntityModel;
    }

    @PostMapping(headers = {"X-API-VERSION=v1"})
    public ResponseEntity<Object> createAirline(@Valid @RequestBody Airline airline) throws AirlineAlreadyExistsException {
        Airline savedAirline = airlineService.save(airline);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAirline.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
    @PutMapping(value = "/{id}", headers = {"X-API-VERSION=v1"})
    public ResponseEntity<Object> updateAirline(@Valid @RequestBody Airline airline, @PathVariable Long id) throws AirlineNotFoundException {
        airlineService.update(id, airline);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}", headers = {"X-API-VERSION=v1"})
    public void deleteAirline(@PathVariable Long id) throws AirlineNotFoundException {
        airlineService.delete(id);
    }


}
