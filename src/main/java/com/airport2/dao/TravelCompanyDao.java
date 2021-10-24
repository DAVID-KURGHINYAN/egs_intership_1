package com.airport2.dao;

import com.airport2.entity.TravelCompany;

import java.util.List;
import java.util.Set;

public interface TravelCompanyDao {
    TravelCompany getById(int id);

    List<TravelCompany> getAllTravelCompanies();

    Set<TravelCompany> get(int page, int perPage, String sort);

    void saveTravelCompany(TravelCompany travelCompany);

    TravelCompany update(TravelCompany travelCompany);

    void delete(int travelCompanyId);

    List<TravelCompany> getTravelCompanyOfTrip(int tripNumber);
}
