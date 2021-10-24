package com.airport2.entity;

import javax.persistence.*;


@Entity
@Table(name = "TripToPassengerToTravelCompanyRel")
public class TripToPassengerToTravelCompanyRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "travel_company_id")
    private TravelCompany travelCompany;



    public TripToPassengerToTravelCompanyRel() {
    }

    public TripToPassengerToTravelCompanyRel(Passenger passenger, Trip trip, TravelCompany travelCompany) {
        this.passenger = passenger;
        this.trip = trip;
        this.travelCompany = travelCompany;
    }

    public TripToPassengerToTravelCompanyRel(int id, Passenger passenger, Trip trip, TravelCompany travelCompany) {
        this.id = id;
        this.passenger = passenger;
        this.trip = trip;
        this.travelCompany = travelCompany;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public TravelCompany getTravelCompany() {
        return travelCompany;
    }

    public void setTravelCompany(TravelCompany travelCompany) {
        this.travelCompany = travelCompany;
    }
}
