package com.newAirport.dao;

import com.newAirport.entity.Company;

import java.util.Set;

public interface CompanyDao {

    Company save(Company company);

    Company getById(int id);

    Company update(Company company);

    int delete(int id);

    Set<Company> getAll();

    Set<Company> get(int page, int perPage, String sort);
}
