package com.airlineticket.microservices.airportservice.businessdomain.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Airport {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Size(max = 4)
    @Column(unique = true)
    private String airportCode;
    private String name;
    private String city;
    private String country;

    public Airport(String airportCode, String name, String city, String country) {
        this.airportCode = airportCode;
        this.name = name;
        this.city = city;
        this.country = country;
    }
}
