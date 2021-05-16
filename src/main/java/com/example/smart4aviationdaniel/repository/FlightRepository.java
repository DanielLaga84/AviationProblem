package com.example.smart4aviationdaniel.repository;

import com.example.smart4aviationdaniel.model.Flight;
import com.example.smart4aviationdaniel.repository.dto.FlightDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {
    Optional<Flight> findById (Integer id);

    @Query(
                    value="select id as id,flight_number as flightNumber, departure_date as departureDate, airport_code_arrival as airportCodeArrival," +
                            "airport_code_departure as airportCodeDeparture from flights where departure_date between :date and dateadd(d,1, :date)" +
                            "and flight_number =:flightNumber",
                    nativeQuery=true
            )
    FlightDto findByDepartureDateAndFlightNumber(@Param("date")LocalDate date, @Param("flightNumber")Short flightNumber);

    @Query(
            value="select id as id,flight_number as flightNumber, departure_date as departureDate, airport_code_arrival as airportCodeArrival," +
                    "airport_code_departure as airportCodeDeparture from flights where departure_date between :date and dateadd(d,1, :date)" +
                    "and (airport_code_departure = :airportCode or airport_code_arrival= :airportCode)",
            nativeQuery=true
    )
    Set<FlightDto> findByDepartureDateAndAirportCode(@Param("date")LocalDate date, @Param("airportCode")String airportCode);

}
