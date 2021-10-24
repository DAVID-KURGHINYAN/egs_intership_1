package com.airport2.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "trip_number")
    private int tripNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "air_company_id")
    private AirCompany airCompany;

    @Column(name = "timeIn")
    private LocalDate timeIn;

    @Column(name = "timeOut")
    private LocalDate timeOut;

    @Column(name = "townTo")
    private String townTo;

    @Column(name = "townFrom")
    private String townFrom;

    @OneToMany(mappedBy = "trip")
    private List<TripToPassengerToTravelCompanyRel> tripToPassengerToTravelCompanyRels;

    @Transient
    private List<Passenger> passengers;
    @Transient
    private List<TravelCompany> travelCompanies;


    public Trip() {
    }

    public Trip(int tripNumber, AirCompany airCompany, LocalDate timeIn,
                LocalDate timeOut, String townTo, String townFrom) {
        this.tripNumber = tripNumber;
        this.airCompany = airCompany;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.townTo = townTo;
        this.townFrom = townFrom;

    }

    public Trip(int id, int tripNumber, AirCompany airCompany, LocalDate timeIn, LocalDate timeOut, String townTo,
                String townFrom) {
        this.id = id;
        this.tripNumber = tripNumber;
        this.airCompany = airCompany;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.townTo = townTo;
        this.townFrom = townFrom;
    }

    public Trip(int id, int tripNumber, AirCompany airCompany, LocalDate timeIn, LocalDate timeOut, String townTo,
                String townFrom, List<Passenger> passengers, List<TravelCompany> travelCompanies) {
        this.id = id;
        this.tripNumber = tripNumber;
        this.airCompany = airCompany;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.townTo = townTo;
        this.townFrom = townFrom;
        this.passengers = passengers;
        this.travelCompanies = travelCompanies;
    }

    public Trip(int id, int tripNumber, LocalDate timeIn, LocalDate timeOut, String townTo, String townFrom) {
        this.id = id;
        this.tripNumber = tripNumber;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.townTo = townTo;
        this.townFrom = townFrom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(int tripNumber) {
        this.tripNumber = tripNumber;
    }

    public AirCompany getAirCompany() { return airCompany; }

    public void setAirCompany(AirCompany airCompany) { this.airCompany = airCompany; }

    public LocalDate getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDate timeIn) {
        this.timeIn = timeIn;
    }

    public LocalDate getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDate timeOut) {
        this.timeOut = timeOut;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(String townTo) {
        this.townTo = townTo;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<TravelCompany> getTravelCompanies() {
        return travelCompanies;
    }

    public void setTravelCompanies(List<TravelCompany> travelCompanies) {
        this.travelCompanies = travelCompanies;
    }

    public List<TripToPassengerToTravelCompanyRel> getTripToPassengerToTravelCompanyRels() {
        return tripToPassengerToTravelCompanyRels;
    }

    public void setTripToPassengerToTravelCompanyRels(List<TripToPassengerToTravelCompanyRel> tripToPassengerToTravelCompanyRels) {
        this.tripToPassengerToTravelCompanyRels = tripToPassengerToTravelCompanyRels;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", tripNumber=" + tripNumber +
                ", airCompany=" + airCompany +
                ", timeIn=" + timeIn +
                ", timeOut=" + timeOut +
                ", townTo='" + townTo + '\'' +
                ", townFrom='" + townFrom + '\'' +
                ", passengers=" + passengers +
                ", travelCompanies=" + travelCompanies +
                '}';
    }
}