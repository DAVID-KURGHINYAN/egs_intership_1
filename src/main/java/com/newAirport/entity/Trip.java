package com.newAirport.entity;

import java.time.LocalDate;

public class Trip {

    private int id;
    private int tripNumber;
    private Company company;
    private LocalDate timeIn;
    private LocalDate timeOut;
    private String townTo;
    private String townFrom;
    private Passenger passenger;

    public Trip(int id, int tripNumber, Company company, LocalDate timeIn, LocalDate timeOut,
                String townTo, String townFrom, Passenger passenger) {
        this.id = id;
        this.tripNumber = tripNumber;
        this.company = company;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.townTo = townTo;
        this.townFrom = townFrom;
        this.passenger = passenger;
    }

    public Trip() {

    }

    public Trip(int id, Company company, LocalDate timeIn, LocalDate timeOut, String townTo, String townFrom) {
        this.id = id;
        this.company = company;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

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

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", tripNumber=" + tripNumber +
                ", company=" + company +
                ", timeIn=" + timeIn +
                ", timeOut=" + timeOut +
                ", townTo='" + townTo + '\'' +
                ", townFrom='" + townFrom + '\'' +
                ", passenger=" + passenger +
                '}';
    }
}
