package com.example.smart4aviationdaniel.repository;

import com.example.smart4aviationdaniel.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CargoRepository extends JpaRepository<Flight.CargoAbs.Cargo, Integer> {

    <T> Set<T> findBy(Class<T> type);

    List<Flight.CargoAbs.Cargo> findByFlightId (Integer flightId);
}
