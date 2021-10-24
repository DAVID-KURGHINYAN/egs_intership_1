package com.airport2.dao;


import com.airport2.entity.Trip;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class TripDaoImpl implements TripDao{
    @Override
    public Trip getById(int id) {
        return null;
    }

    @Override
    public Set<Trip> getAll() {
        return null;
    }

    @Override
    public Set<Trip> getByPage(int page, int perPage, String sort) {
        return null;
    }

    @Override
    public Trip save(Trip trip) {
        return null;
    }

    @Override
    public Trip update(Trip trip) {
        return null;
    }

    @Override
    public int delete(int tripId) {
        return 0;
    }

    @Override
    public Collection<? extends com.airport2.entity.Trip> getTripsFrom(String townFrom) {
        return null;
    }

    @Override
    public List<Trip> getTripsTo(String townTo) {
        return null;
    }
}
