package com.airlineticket.microservices.airlineservice.businessdomain.services.plane;

import com.airlineticket.microservices.airlineservice.businessdomain.domains.Airplane;

import java.util.List;

public interface IPlaneService {
    List<Airplane> getAll(Long airlineId);
    Airplane getOne(Long id);
    Airplane save(Long airlineId, Airplane airplane);
    Airplane update( Long id, Airplane airplane);
    void delete(Long id);
    void saveAll(Long id, int quantity, Airplane airplane);
}
