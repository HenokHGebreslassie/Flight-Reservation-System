package com.airlineticket.microservices.airlineservice.businessdomain.domains;

import com.airlineticket.microservices.airlineservice.businessdomain.domains.Enums.Class;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Class seatType;
    @Column(name = "`column`")
    private Integer column;
    @Column(name = "`row`")
    private Integer row;
    private String side;
    @Column(columnDefinition = "boolean default false")
    private Boolean reserved;
    @ManyToOne
    private Airplane airplane;
    @JsonIgnore
    @Version
    private Integer version;

    public Seat(Class seatType, Integer column, Integer row, String side, Boolean reserved) {
        this.seatType = seatType;
        this.column = column;
        this.row = row;
        this.side = side;
        this.reserved = reserved;
    }
}
