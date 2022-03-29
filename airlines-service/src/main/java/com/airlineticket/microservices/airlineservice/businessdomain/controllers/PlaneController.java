package com.airlineticket.microservices.airlineservice.businessdomain.controllers;

import com.airlineticket.microservices.airlineservice.businessdomain.domains.Airline;
import com.airlineticket.microservices.airlineservice.businessdomain.domains.Airplane;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.airline.AirlineNotFoundException;
import com.airlineticket.microservices.airlineservice.businessdomain.exceptions.plane.PlaneNotFoundException;
import com.airlineticket.microservices.airlineservice.businessdomain.services.plane.IPlaneService;
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
public class PlaneController {
    @Autowired
    private IPlaneService planeService;

    @GetMapping(value = "/airlines/{airlineId}/planes", headers = {"X-API-VERSION=v1"})
    public List<Airplane> getAll(@PathVariable Long airlineId ) {
        return planeService.getAll(airlineId);
    }

    @GetMapping(value = "/airplanes/{id}", headers = {"X-API-VERSION=v1"} )
    public EntityModel<Airplane> getPlane(@PathVariable Long id) throws PlaneNotFoundException {
        System.out.println("herrerereeee");
        Airplane airplane = planeService.getOne(id);
        EntityModel<Airplane> planeEntityModel = EntityModel.of(airplane);
        WebMvcLinkBuilder linkToPlanes = linkTo(methodOn(this.getClass()).getAll(airplane.getAirline().getId()));
        WebMvcLinkBuilder linkToSeats = linkTo(methodOn(SeatController.class).
                getAll(id));
        planeEntityModel.add((linkToPlanes).withRel("all-planes"));
        planeEntityModel.add((linkToSeats).withRel("all-seats"));
        return planeEntityModel;
    }

    @PostMapping(value = "/airlines/{airlineId}/planes", headers = {"X-API-VERSION=v1"})
    public ResponseEntity<Object> createPlane(@PathVariable Long airlineId,@Valid @RequestBody Airplane airplane) throws AirlineNotFoundException {
        Airplane savedAirplane = planeService.save(airlineId, airplane);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAirplane.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
    @PutMapping(value = "/airplanes/{id}", headers = {"X-API-VERSION=v1"})
    public ResponseEntity<Object> updatePlane( @Valid @RequestBody Airplane airplane, @PathVariable Long id) {
        planeService.update( id, airplane);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/airplanes/{id}", headers = {"X-API-VERSION=v1"})
    public void deletePlane( @PathVariable Long id) {
        planeService.delete(id);
    }

    @PostMapping(value = "/airlines/{airlineId}/planes", headers = {"X-API-VERSION=v1"}, params = "quantity")
    public ResponseEntity<Object> createPlanes(@PathVariable Long airlineId, @RequestParam int quantity, @Valid @RequestBody Airplane airplane ) throws AirlineNotFoundException {
        //todo-fix query param being added issue in location header of the response
        planeService.saveAll(airlineId, quantity, airplane);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("").build().toUri();
        return ResponseEntity.created(location).build();
    }
}
