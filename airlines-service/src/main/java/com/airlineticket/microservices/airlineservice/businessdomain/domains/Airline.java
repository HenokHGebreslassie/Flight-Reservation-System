package com.airlineticket.microservices.airlineservice.businessdomain.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Airline {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "airline")
    private Set<Airplane> airplanes;
    @JsonIgnore
    @Version
    private Integer version;
    public Airline(String name) {
        this.name = name;
    }
}
