package com.airlineticket.microservices.airlineservice.businessdomain.repositories;

import com.airlineticket.microservices.airlineservice.businessdomain.domains.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface IPlaneDao extends JpaRepository<Airplane, Long> {
    List<Airplane> findAllByAirline_Id(Long id);
}
