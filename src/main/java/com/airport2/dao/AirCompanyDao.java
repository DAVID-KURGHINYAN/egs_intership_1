package com.airport2.dao;

import com.airport2.entity.AirCompany;

import java.util.List;
import java.util.Set;

public interface AirCompanyDao {

    AirCompany getById(int id);

    List<AirCompany> getAllAirCompanies();

    Set<AirCompany> get(int page, int perPage, String sort);

    void saveAirCompany(AirCompany airCompany);

    AirCompany update(AirCompany airCompany);

    void delete(int airCompanyId);
}
