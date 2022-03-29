package com.airlineticket.microservices.airlineservice.businessdomain.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String serialNumber;
    @Embedded
    private PlaneModel planeModel;
    @JsonIgnore
    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL)
    Set<Seat> seats;
    @JsonIgnore
    @ManyToOne
    private Airline airline;
    @JsonIgnore
    @Version
    private Integer version;
}
