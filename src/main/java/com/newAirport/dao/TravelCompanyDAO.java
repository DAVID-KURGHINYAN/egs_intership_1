package com.newAirport.dao;


import com.newAirport.entity.TravelCompany;

import java.sql.SQLException;
import java.util.Set;

public interface TravelCompanyDAO {

    Object getById(int id) throws SQLException;

    Set getAll() throws SQLException;

    Set getByPage(int page, int perPage, String sort);

    int delete(int id);

    TravelCompany save(TravelCompany travel);

    TravelCompany update(TravelCompany travel);
}
