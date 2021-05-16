package com.example.smart4aviationdaniel.service;

import com.example.smart4aviationdaniel.repository.BaggageRepostitory;
import com.example.smart4aviationdaniel.repository.CargoRepository;
import com.example.smart4aviationdaniel.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FlightService
{
    private FlightRepository flightRepository;
    private CargoRepository cargoRepository;
    private BaggageRepostitory baggageRepository;

    FlightService (FlightRepository flightRepository, CargoRepository cargoRepository, BaggageRepostitory baggageRepository)
    {
        this.flightRepository = flightRepository;
        this.cargoRepository = cargoRepository;
        this.baggageRepository=baggageRepository;
    }

    public double cargoWeight(Integer flightId)
    {

        return cargoRepository.findByFlightId(flightId).stream().map(c -> {
            double weight=c.getWeight();
            if (c.getWeightUnit().equals("lb"))	weight=weight/2.2046;
            return c.getPiecesOfCargo()*weight;
        }).collect(Collectors.summingDouble(Double::doubleValue));

    }

    public double baggageWeight(Integer flightId)
    {

        return baggageRepository.findByFlightId(flightId).stream().map(c -> {
            double weight=c.getWeight();
            if (c.getWeightUnit().equals("lb"))	weight=weight/2.2046;
            return c.getPiecesOfCargo()*weight;
        }).collect(Collectors.summingDouble(Double::doubleValue));

    }

    public long numberOfFlightsDepartingFromAirport(LocalDate date, String airportCode)
    {
        return flightRepository.findByDepartureDateAndAirportCode(date,airportCode).stream().filter(f-> f.getAirportCodeDeparture().equals(airportCode)).count();
    }

    public long numberOfFlightsArrivingToTheAirport(LocalDate date, String airportCode)
    {
        return flightRepository.findByDepartureDateAndAirportCode(date,airportCode).stream().filter(f-> f.getAirportCodeArrival().equals(airportCode)).count();
    }

    int totalNumberOfBaggageOfOneFlight(Integer flightId)
    {
        return baggageRepository.findByFlightId(flightId).stream().map(b-> b.getPiecesOfCargo()).collect(Collectors.summingInt(Short::shortValue));
    }

    public int totalNumberOfBaggageArrivingToTheAirport(LocalDate date, String airportCode)
    {
        return flightRepository.findByDepartureDateAndAirportCode(date, airportCode).stream()
                .filter(f->f.getAirportCodeArrival().equals(airportCode))
                .map(f->totalNumberOfBaggageOfOneFlight(f.getId()))
                .collect(Collectors.summingInt(Integer::intValue));
    }

    public int totalNumberOfBaggageDepartingFromTheAirport(LocalDate date, String airportCode)
    {
        return flightRepository.findByDepartureDateAndAirportCode(date, airportCode).stream()
                .filter(f->f.getAirportCodeDeparture().equals(airportCode))
                .map(f->totalNumberOfBaggageOfOneFlight(f.getId()))
                .collect(Collectors.summingInt(Integer::intValue));
    }




}