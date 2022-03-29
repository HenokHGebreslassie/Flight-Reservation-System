package com.airlineticket.microservices.airlineservice.businessdomain.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class PlaneModel {
    private String factory;
    private String model;
    private String name;
}
