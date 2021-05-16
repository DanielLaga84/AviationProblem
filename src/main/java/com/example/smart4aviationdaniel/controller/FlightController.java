package com.example.smart4aviationdaniel.controller;

import ch.qos.logback.core.boolex.EvaluationException;
import com.example.smart4aviationdaniel.repository.BaggageRepostitory;
import com.example.smart4aviationdaniel.repository.CargoRepository;
import com.example.smart4aviationdaniel.repository.FlightRepository;
import com.example.smart4aviationdaniel.service.FlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
class FlightController {
    private final FlightService service;
    private final CargoRepository cargoRepository;
    private final FlightRepository flightRepository;
    private final BaggageRepostitory baggageRepository;

    FlightController(FlightService service, FlightRepository flightRepository, CargoRepository cargoRepository, BaggageRepostitory baggageRepository) {
        this.service = service;
        this.flightRepository = flightRepository;
        this.cargoRepository = cargoRepository;
        this.baggageRepository = baggageRepository;
    }

    @RequestMapping(value = "/task1")
    @ResponseBody
    public String task1(@RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam(value = "flight-number") Short flightNumber) {
        int flightId = flightRepository.findByDepartureDateAndFlightNumber(date, flightNumber).getId();

        int cargoW = (int) service.cargoWeight(flightId);
        int baggageW = (int) service.baggageWeight(flightId);

        return "For flight " + flightNumber + " and date " + date +
                " total weight of cargo " + cargoW + " kg., " +
                "total weight of baggage " + baggageW + " kg., " +
                "and total weight" + (cargoW + baggageW) + " kg.";
    }

    @RequestMapping(value = "/task2")
    @ResponseBody
    public String task2(@RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam(value = "airport-code") String airportCode) {
        return "For airport " + airportCode + " on " + date + " there are" +
                service.numberOfFlightsDepartingFromAirport(date, airportCode) +
                " flights departing with " +
                service.totalNumberOfBaggageDepartingFromTheAirport(date, airportCode)
                + " pieces of baggage" +
                " and "
                + service.numberOfFlightsArrivingToTheAirport(date, airportCode) +
                " flights arriving with "
                + service.totalNumberOfBaggageArrivingToTheAirport(date, airportCode)
                + " pieces of baggage.";

    }

}



