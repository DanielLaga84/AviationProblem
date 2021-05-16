package com.example.smart4aviationdaniel.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    @Column(name = "id")
    int id;
    @Column(name = "flight_number")
    short flightNumber;
    @Column(name = "airport_code_departure")
    String airportCodeDeparture;
    @Column(name = "airport_code_arrival")
    String airportCodeArrival;
    @Column(name = "departure_date")
    LocalDateTime departureDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    Set<CargoAbs.Baggage> baggage = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    Set<CargoAbs.Cargo> cargo = new HashSet<>();

    public int getId() {
        return id;
    }

    public short getFlightNumber() {
        return flightNumber;
    }

    public String getAirportCodeDeparture() {
        return airportCodeDeparture;
    }

    public String getAirportCodeArrival() {
        return airportCodeArrival;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public Set<CargoAbs.Baggage> getBaggage() {
        return baggage;
    }

    public Set<CargoAbs.Cargo> getCargo() {
        return cargo;
    }

    public Flight() {
    }

    public Flight(int id, short flightNumber, String airportCodeDeparture, String airportCodeArrival, LocalDateTime departureDate, Set<CargoAbs.Baggage> baggage, Set<CargoAbs.Cargo> cargo) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.airportCodeDeparture = airportCodeDeparture;
        this.airportCodeArrival = airportCodeArrival;
        this.departureDate = departureDate;
        this.baggage = baggage;
        this.cargo = cargo;
    }

    @Entity
    @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
    public static class CargoAbs {
        @Id
        @Column(name = "id")
        int id;
        @Column(name = "pieces_of_cargo")
        short piecesOfCargo;
        @Column(name = "weight")
        short weight;
        @Enumerated(EnumType.STRING)
        @Column(name = "weight_unit")
        WeightUnit weightUnit;

        @JoinColumn(name = "flight_id")
        private int flightId;

        enum WeightUnit {kg, lb}

        public int getId() {
            return id;
        }

        public short getPiecesOfCargo() {
            return piecesOfCargo;
        }


        public short getWeight() {
            return weight;
        }

        public WeightUnit getWeightUnit() {
            return weightUnit;
        }


        public int getFlightId() {
            return flightId;
        }


        @Entity
        @Table(name = "cargo")
        public static class Cargo extends CargoAbs {

            public Cargo() {
            }

            public Cargo(int id, short piecesOfCargo, short weight, WeightUnit weightUnit) {
                this.id = id;
                this.piecesOfCargo = piecesOfCargo;
                this.weight = weight;
                this.weightUnit = weightUnit;
            }
            public int getId() {
                return id;
            }

            public short getPiecesOfCargo() {
                return piecesOfCargo;
            }


            public short getWeight() {
                return weight;
            }

            public WeightUnit getWeightUnit() {
                return weightUnit;
            }


        }

        @Entity
        @Table(name = "baggage")
        public static class Baggage extends CargoAbs {
            public Baggage() {
            }

            public Baggage(int id, short piecesOfCargo, short weight, WeightUnit weightUnit) {
                this.id = id;
                this.piecesOfCargo = piecesOfCargo;
                this.weight = weight;
                this.weightUnit = weightUnit;
            }
            public int getId() {
                return id;
            }

            public short getPiecesOfCargo() {
                return piecesOfCargo;
            }


            public short getWeight() {
                return weight;
            }

            public WeightUnit getWeightUnit() {
                return weightUnit;
            }

        }
    }
}
