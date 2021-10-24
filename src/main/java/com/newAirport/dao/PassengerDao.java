package com.newAirport.dao;

import com.newAirport.entity.Passenger;
import com.newAirport.entity.Trip;

import java.util.List;
import java.util.Set;


public interface PassengerDao {

    Passenger getById(int id);

    Set<Passenger> getAll();

    Set<Passenger> get(int page, int perPage, String sort);

    Passenger save(Passenger passenger);

    Passenger update(Passenger passenger);

    int delete(int passengerId);

    List<Passenger> getPassengersOfTrip(int tripNumber);

    void registerTrip(Trip trip, Passenger passenger);

    void cancelTrip(int passengerId, int tripNumber);

}
