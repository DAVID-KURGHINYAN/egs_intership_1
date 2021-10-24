package com.airport2.dao;

import com.airport2.entity.Address;

import java.util.List;
import java.util.Set;

public interface AddressDao {

    Address getById(int id);
    void saveAddress(Address address);
    List<Address> getAllAddresses();
    Set<Address> get(int page, int perPage, String sort);
    Address update(Address address);
    void delete(int addressId);
}
