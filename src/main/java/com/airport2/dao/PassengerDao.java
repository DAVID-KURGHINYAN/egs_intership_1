package com.airport2.dao;

import com.airport2.entity.Passenger;
import com.airport2.entity.TravelCompany;
import com.airport2.entity.Trip;

import java.util.List;
import java.util.Set;

public interface PassengerDao {

    Passenger getById(int id);

    List<Passenger> getAllPassengers();

    Set<Passenger> get(int page, int perPage, String sort);

    void savePassenger(Passenger passenger);

    Passenger update(Passenger passenger);

    void delete(int passengerId);

    List<Passenger> getPassengersOfTrip(int tripNumber);

    void registerTrip(Trip trip, Passenger passenger, TravelCompany travelCompany);

    void cancelTrip(int passengerId, int tripNumber, int travelCompanyId);



}
