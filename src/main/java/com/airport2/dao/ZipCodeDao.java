package com.airport2.dao;

import com.airport2.entity.ZipCode;

import java.util.List;
import java.util.Set;

public interface ZipCodeDao {
    ZipCode getById(int id);

    void saveZipCode(ZipCode zipCode);

    List<ZipCode> getZipCodes();

    Set<ZipCode> get(int page, int perPage, String sort);

    void update(ZipCode zipCode);

    void delete(int zipCodeId);
}
