package com.example.smart4aviationdaniel.service;

import com.example.smart4aviationdaniel.repository.BaggageRepostitory;
import com.example.smart4aviationdaniel.repository.CargoRepository;
import com.example.smart4aviationdaniel.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final BaggageRepostitory baggageRepostitory;
    private final CargoRepository cargoRepository;

    public FlightService(FlightRepository flightRepository, BaggageRepostitory baggageRepostitory, CargoRepository cargoRepository) {
        this.flightRepository = flightRepository;
        this.baggageRepostitory = baggageRepostitory;
        this.cargoRepository = cargoRepository;
    }

    public double cargoWeight ( Integer flightId) {
        return cargoRepository.findByFlightId(flightId).stream().map(c -> {
            double weight = c.getWeight();
            if (Objects.equals(c.getWeightUnit(), "lb")) weight = weight / 2.2046;
            return c.getPiecesOfCargo() * weight;
        }).collect(Collectors.summingDouble(Double::doubleValue));
    }
       public double baggageWeight (Integer flightId){

            return baggageRepostitory.findByFlightId(flightId).stream().map(c -> {
                double weight=c.getWeight();
                if (Objects.equals(c.getWeightUnit(), "lb"))	weight=weight/2.2046;
                return c.getPiecesOfCargo()*weight;
            }).collect(Collectors.summingDouble(Double::doubleValue));

        }

         public long numberOfFlightsDepartingFromAirport (LocalDate date, String airportCode)
        {
            return flightRepository.findByDepartureDateAndAirportCode(date,airportCode).stream().filter(f-> f.getAirportCodeDeparture().equals(airportCode)).count();
        }

        public long numberOfFlightsArrivingToTheAirport (LocalDate date, String airportCode)
        {
            return flightRepository.findByDepartureDateAndAirportCode(date,airportCode).stream().filter(f-> f.getAirportCodeArrival().equals(airportCode)).count();
        }

        public int totalNumberOfBaggageOfOneFlight(Integer flightId)
        {
            return baggageRepostitory.findByFlightId(flightId).stream().map(b-> b.getPiecesOfCargo()).collect(Collectors.summingInt(Short::shortValue));
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
