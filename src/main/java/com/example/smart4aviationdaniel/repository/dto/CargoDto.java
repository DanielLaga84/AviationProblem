package com.example.smart4aviationdaniel.repository.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = CargoDto.DeserializationImpl.class)
public interface CargoDto {

    static CargoDto create (final int id,final short piecesOfCargo, short weight, String weightUnit, int flightId)
    {
        return new DeserializationImpl(id, piecesOfCargo,weight,weightUnit,flightId);
    }

    int getId();
    short getPiecesOfCargo();
    short getWeight();
    String getWeightUnit();
    int getFlightId();

    class DeserializationImpl implements CargoDto
    {
        private final int id;
        private final short piecesOfCargo;
        private final short weight;
        private final String weightUnit;
        private final int flightId;

        DeserializationImpl(final int id, final short piecesOfCargo, final short weight, final String weightUnit, int flightId)
        {
            this.id = id;
            this.piecesOfCargo = piecesOfCargo;
            this.weight = weight;
            this.weightUnit = weightUnit;
            this.flightId=flightId;
        }

        @Override
        public int getId() { return id; }

        @Override
        public short getPiecesOfCargo() { return piecesOfCargo; }

        @Override
        public short getWeight() { return weight; }

        @Override
        public String getWeightUnit() {return weightUnit; }

        @Override
        public int getFlightId() {return  flightId;}
    }

}