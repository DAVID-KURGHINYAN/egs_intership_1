package com.airport2.entity;

import com.airport2.dao.AddressDaoImpl;
import com.airport2.dao.TravelCompanyDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TravelCompanyTest {
    @Test
    void getById() {
        TravelCompanyDaoImpl impl = new TravelCompanyDaoImpl();
        AddressDaoImpl address = new AddressDaoImpl();
        address.getById(1);
        TravelCompany travelCompany;
        travelCompany = impl.getById(1);
        TravelCompany travelCompany1 = new TravelCompany();
        travelCompany1.setName("Levon");
        travelCompany1.setAddress(address.getById(1));
        Assertions.assertNotEquals(travelCompany, travelCompany1);
        Assertions.assertEquals(travelCompany.getName(), travelCompany1.getName());

    }

    @Test
    void getAllTravelCompanies() {
        TravelCompanyDaoImpl travelCompanyDAO = new TravelCompanyDaoImpl();
        List<TravelCompany> companyList = new ArrayList<>();
        companyList.addAll(travelCompanyDAO.getAllTravelCompanies());
        TravelCompany travelCompany = travelCompanyDAO.getById(1);
        Assertions.assertNotEquals(companyList.get(0).getId(), travelCompany.getId());
        Assertions.assertNotEquals(companyList.get(0).getName(), travelCompany.getName());

    }


    @Test
    void saveTravelCompanyAndDelete() {
        TravelCompanyDaoImpl travelCompanyDAO = new TravelCompanyDaoImpl();
        TravelCompany travelCompany = new TravelCompany();
        TravelCompany travelCompany1;
        Address address = new Address();
        ZipCode zipCode = new ZipCode();
        zipCode.setCity("Yerevan");
        zipCode.setCountry("Armenia");
        zipCode.setPostalCode("0025");
        address.setStreet("Tumanyan");
        address.setZipCode(zipCode);
        travelCompany.setAddress(address);
        travelCompany.setName("Levon");
        travelCompanyDAO.saveTravelCompany(travelCompany);
        travelCompany1 = travelCompanyDAO.getById(1);
        int id = travelCompany.getId();
        Assertions.assertNotEquals(travelCompany, travelCompany1);
        travelCompanyDAO.delete(id)
        ;
        Assertions.assertNotEquals(0, id);

    }

    @Test
    void update() {

        TravelCompanyDaoImpl travelCompanyDAO = new TravelCompanyDaoImpl();
        TravelCompany travelCompany = new TravelCompany();
        TravelCompany travelCompany1;
        Address address = new Address();
        ZipCode zipCode = new ZipCode();
        zipCode.setCity("Yerevan");
        zipCode.setCountry("Armenia");
        zipCode.setPostalCode("0025");
        address.setStreet("Tumanyan");
        address.setZipCode(zipCode);
        travelCompany.setAddress(address);
        travelCompany.setName("Levon");
        travelCompany1 = travelCompanyDAO.getById(1);
        travelCompanyDAO.update(travelCompany1);
        Assertions.assertNotEquals(travelCompany, travelCompany1);
    }
}