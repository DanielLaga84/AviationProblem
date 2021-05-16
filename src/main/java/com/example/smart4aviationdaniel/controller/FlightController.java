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
public class FlightController {

    private final FlightService flightService;
    private final CargoRepository cargoRepository;
    private final BaggageRepostitory baggageRepostitory;
    private final FlightRepository flightRepository;

    public FlightController(FlightService flightService, CargoRepository cargoRepository, BaggageRepostitory baggageRepostitory, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.cargoRepository = cargoRepository;
        this.baggageRepostitory = baggageRepostitory;
        this.flightRepository = flightRepository;
    }

    @RequestMapping(value = "/task1")
    @ResponseBody
    public String task1(@RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date, @RequestParam(value = "flight-number") Short flightNumber) {
        int flightId = flightRepository.findByDepartureDateAndFlightNumber(date, flightNumber).getId();

        int cargoW= (int) flightService.cargoWeight(flightId);
        int baggageW = (int) flightService.baggageWeight(flightId);

        return "For flight "+flightNumber+" and date "+date +
                " total weight of cargo "+ cargoW+ " kg., "+
                "total weight of baggage "+ baggageW + " kg., "+
                "and total weight" + (cargoW + baggageW) +" kg.";
    }
    @RequestMapping(value="/task2")
    @ResponseBody
    public String task2(@RequestParam(value="date") @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate date, @RequestParam(value="airport-code")String airportCode)
    {
        return "For airport "+ airportCode +" on "+date+ " there are" +
                flightService.numberOfFlightsDepartingFromAirport (date, airportCode) +
                " flights departing with "+
                flightService.totalNumberOfBaggageDepartingFromTheAirport(date,airportCode)
                +" pieces of baggage" +
                " and "
                + flightService.numberOfFlightsArrivingToTheAirport (date, airportCode) +
                " flights arriving with "
                + flightService.totalNumberOfBaggageArrivingToTheAirport(date,airportCode)
                + " pieces of baggage.";

    }


}
