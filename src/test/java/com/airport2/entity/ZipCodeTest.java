package com.airport2.entity;

import com.airport2.dao.ZipCodeDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ZipCodeTest {

    @Test
    void getById() {
        ZipCodeDaoImpl zipCodeDAO =new ZipCodeDaoImpl();
        zipCodeDAO.getById(1);
        ZipCode zipCode;
        zipCode= zipCodeDAO.getById(1);
        ZipCode zipCode1=new ZipCode();
        zipCode1.setCountry("Armenia");
        zipCode1.setCity("Yerevan");
        zipCode1.setPostalCode("0025");
        Assertions.assertNotEquals(zipCode,zipCode1);
        Assertions.assertNotEquals(zipCode.getCity(),zipCode1.getCity());
    }

    @Test
    void getZipCodes() {
        ZipCodeDaoImpl zipCodeDAO=new ZipCodeDaoImpl();
        List<ZipCode> zipCodeList=new ArrayList<>();
        zipCodeList.addAll(zipCodeDAO.getZipCodes());
        ZipCode zipCode=zipCodeDAO.getById(1);
        Assertions.assertEquals(zipCodeList.get(0).getId(),zipCode.getId());
        Assertions.assertEquals(zipCodeList.get(0).getCity(),zipCode.getCity());
    }


    @Test
    void saveZipCodeAndDelete() {
        ZipCodeDaoImpl zipCodeDAO=new ZipCodeDaoImpl();
        ZipCode zipCode=new ZipCode();
        ZipCode zipCode1;
        zipCode.setCity("Yerevan");
        zipCode.setCountry("Armenia");
        zipCode.setPostalCode("0025");
        zipCodeDAO.saveZipCode(zipCode);
        zipCode1=zipCodeDAO.getById(1);
        int id=zipCode.getId();
        Assertions.assertNotEquals(zipCode,zipCode1);
        zipCodeDAO.delete(id)
        ;
        Assertions.assertNotEquals(0,id);
    }

    @Test
    void update() {
        ZipCodeDaoImpl zipCodeDAO=new ZipCodeDaoImpl();
        ZipCode zipCode=new ZipCode();
        ZipCode zipCode1;
        zipCode.setCity("Yerevan");
        zipCode.setCountry("Armenia");
        zipCode.setPostalCode("0025");
        zipCodeDAO.saveZipCode(zipCode);
        zipCode1=zipCodeDAO.getById(1);
        int id=zipCode.getId();
        zipCodeDAO.update(zipCode1);
        Assertions.assertNotEquals(zipCode,zipCode1);
        Assertions.assertEquals(zipCode.getId(),id);
    }
}