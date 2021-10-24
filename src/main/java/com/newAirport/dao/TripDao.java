package com.newAirport.dao;

import com.newAirport.entity.Trip;

import java.util.List;
import java.util.Set;

public interface TripDao {
    Trip getById(int id);

    Set<Trip> getAll();

    Set<Trip> getByPage(int page, int perPage, String sort);

    Trip save(Trip trip);

    Trip update(Trip trip);

    int delete(int tripId);

    List<Trip> getTripsFrom(String townFrom);

    List<Trip> getTripsTo(String townTo);
}
