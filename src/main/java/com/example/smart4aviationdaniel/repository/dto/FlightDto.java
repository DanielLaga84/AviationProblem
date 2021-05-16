package com.example.smart4aviationdaniel.repository.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

@JsonDeserialize (as = FlightDto.DeserializationImpl.class)
public interface FlightDto
{

    static FlightDto create (final int id, final Integer flightId,
                             final String airportCodeDeparture,
                             final String airportCodeArrival,
                             final LocalDateTime departureDate)

    {
        return new DeserializationImpl(id,flightId,airportCodeDeparture,airportCodeArrival,departureDate);
    }

    int getId();
    Integer getFlightId();
    String getAirportCodeDeparture();
    String getAirportCodeArrival();
    LocalDateTime getDepartureDate();


    class DeserializationImpl implements FlightDto
    {
        private final int id;
        private final Integer flightId;
        private final String airportCodeDeparture;
        private final String airportCodeArrival;
        private final LocalDateTime departureDate;


        DeserializationImpl(final int id, final Integer flightId, final String airportCodeDeparture, final String airportCodeArrival,
                            final LocalDateTime departureDate)
        {
            this.id = id;
            this.flightId = flightId;
            this.airportCodeDeparture = airportCodeDeparture;
            this.airportCodeArrival = airportCodeArrival;
            this.departureDate = departureDate;
        }

        @Override
        public int getId()
        {
            return id;
        }

        @Override
        public Integer getFlightId()
        {
            return flightId;
        }

        @Override
        public LocalDateTime getDepartureDate()
        {
            return departureDate;
        }

        @Override
        public String getAirportCodeDeparture()
        {
            return airportCodeDeparture;
        }

        @Override
        public String getAirportCodeArrival()
        {
            return airportCodeArrival;
        }

    }
}