package com.airport2.entity;

import com.airport2.dao.AddressDaoImpl;
import com.airport2.dao.AirCompanyDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class AirCompanyTest {
    AirCompanyDaoImpl impl = new AirCompanyDaoImpl();

    @Test
    void getById() {

        AddressDaoImpl address = new AddressDaoImpl();
        address.getById(1);
        AirCompany airCompany;
        airCompany = impl.getById(1);
        AirCompany airCompany1 = new AirCompany();
        airCompany1.setName("Levon");
        airCompany1.setAddress(address.getById(1));
        Assertions.assertNotEquals(airCompany, airCompany1);
        Assertions.assertNotEquals(airCompany.getName(), airCompany1.getName());


    }

    @Test
    void getAllAirCompanies() {
        AirCompanyDaoImpl airCompanyDAO = new AirCompanyDaoImpl();
        List<AirCompany> list = new ArrayList<>();
        list.addAll(airCompanyDAO.getAllAirCompanies());
        AirCompany airCompany = airCompanyDAO.getById(1);
        Assertions.assertEquals(list.get(0).getId(), airCompany.getId());
        Assertions.assertEquals(list.get(0).getName(), airCompany.getName());
    }


    @Test
    void saveAirCompanyAndDelete() {
        AirCompany airCompany = new AirCompany();
        AirCompany airCompany1;
        Address address = new Address();
        ZipCode zipCode = new ZipCode();
        zipCode.setCity("Yerevan");
        zipCode.setCountry("Armenia");
        zipCode.setPostalCode("0025");
        address.setStreet("Tumanyan");
        address.setZipCode(zipCode);
        airCompany.setFoundDate(LocalDate.parse("2020-10-12"));
        airCompany.setName("Armavia");
        impl.saveAirCompany(airCompany);
        airCompany1 = impl.getById(1);
        int id = airCompany.getId();
        Assertions.assertNotEquals(airCompany, airCompany1);
        impl.delete(id)
        ;
        Assertions.assertNotEquals(0, id);
    }

    @Test
    void update() {
        AirCompanyDaoImpl impl = new AirCompanyDaoImpl();
        AirCompany airCompany = new AirCompany();
        AirCompany airCompany1;
        Address address = new Address();
        ZipCode zipCode = new ZipCode();
        zipCode.setCity("Yerevan");
        zipCode.setCountry("Armenia");
        zipCode.setPostalCode("0025");
        address.setStreet("Tumanyan");
        address.setZipCode(zipCode);
        airCompany.setFoundDate(LocalDate.parse("2020-10-12"));
        airCompany.setName("Armavia");
        airCompany1 = impl.getById(1);
        impl.update(airCompany1);
        Assertions.assertNotEquals(airCompany, airCompany1);
    }
}