package com.newAirport.dao;

import com.newAirport.connector.ConnectToDB;
import com.newAirport.entity.Address;
import com.newAirport.entity.Company;
import com.newAirport.entity.Passenger;
import com.newAirport.entity.Trip;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {
    ConnectToDB connectToDB = new ConnectToDB();

    @Test
    public void connect() {
        Connection connection = connectToDB.connect();
        Assert.assertTrue(connection != null);
        connectToDB.disconnect();
    }

    @Test
    public void saveAndDeletePassenger(){
        PassengerDao passengerDao = new PassengerDaoImpl();
        Address address = new Address("Armenia", "Yerevan");
        Passenger passenger = new Passenger("John", "Snow", address);
        Passenger savedPassenger = passengerDao.save(passenger);
        int idOfSavedPassenger = savedPassenger.getId();
        int i = passengerDao.delete(idOfSavedPassenger);
        Assert.assertNotEquals(0, savedPassenger.getId());
        Assert.assertNotEquals(0, i);
    }

    @Test
    public void getById() {
        PassengerDao passengerDao = new PassengerDaoImpl();
        Address address = new Address("Armenia", "Yerevan");
        Passenger passenger = new Passenger("John", "Snow", address);
        Passenger savedPassenger = passengerDao.save(passenger);
        Assert.assertEquals(passenger.getName(), passengerDao.getById(savedPassenger.getId()).getName());
        Assert.assertEquals(passenger.getPhone(), passengerDao.getById(savedPassenger.getId()).getPhone());
        Assert.assertEquals(passenger.getAddress().getId(), passengerDao.getById(savedPassenger.getId())
                        .getAddress().getId());
    }

    @Test
    public void getAll() {
        PassengerDao passengerDAO= new PassengerDaoImpl();
        int size = 0;
        String sql1 = "SELECT * FROM passenger";
        ResultSet rs;
        connectToDB.connect();
        try {
            rs = connectToDB.select(sql1);
            rs.last();
            size = rs.getRow();
            connectToDB.disconnect();
        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        Assertions.assertEquals(size, passengerDAO.getAll().size());

    }

    @Test
    public void getByPage() {
        PassengerDao passengerDao = new PassengerDaoImpl();
        Set<Passenger> set = passengerDao.get(2, 20, "name");
        String name = "";
        for (Passenger pass: set){
            if (!name.equals("")){
                int comp = name.compareTo(pass.getName());
                Assert.assertTrue(comp <= 0);
            }
            name = pass.getName();
        }
        Assert.assertTrue(set.size() <= 20);
    }

    @Test
    public void update() {
        PassengerDao passengerDAO = new PassengerDaoImpl();
        Passenger passenger = passengerDAO.getById(3);
        String name = passenger.getName();
        String phone = passenger.getPhone();

        passenger.setName("Garik");
        passenger.setPhone("3214653");
        passengerDAO.update(passenger);
        passenger = passengerDAO.getById(3);
        assertNotEquals(name, passenger.getName());
        assertNotEquals(phone, passenger.getPhone());
    }

    @Test
    public void getPassengersOfTrip() {
        PassengerDao passengerDAO = new PassengerDaoImpl();
        CompanyDao companyDAO = new CompanyDaoImpl();
        Passenger passenger = passengerDAO.getById(3);
        Company company = companyDAO.getById(4);
        Trip trip = new Trip(1, 2333, company, LocalDate.parse("2021-10-10"),
                LocalDate.parse("2021-10-13"), "gg", "hh", passenger);
        passengerDAO.registerTrip(trip, passenger);
        boolean hasThePassenger = false;
        List<Passenger> list = passengerDAO.getPassengersOfTrip(2333);
        for (Passenger value : list) {
            if (value.getId() == 3) {
                hasThePassenger = true;
                break;
            }
        }
        Assert.assertTrue(hasThePassenger);
    }

    @Test
    public void registerTrip() {
        PassengerDao passengerDAO = new PassengerDaoImpl();
        CompanyDao companyDao = new CompanyDaoImpl();
        Passenger passenger = passengerDAO.getById(500);
        Company company = companyDao.getById(4);
        Trip trip = new Trip(1, 2333, company, LocalDate.parse("2021-10-10"),
                LocalDate.parse("2021-10-13"), "gg", "hh", passenger);
        List<Passenger> list = passengerDAO.getPassengersOfTrip(2333);
        passengerDAO.registerTrip(trip, passenger);
        List<Passenger> list1 = passengerDAO.getPassengersOfTrip(2333);
        Assert.assertTrue(list1.size() - list.size() == 1);
    }

    @Test
    public void cancelTrip() {
        PassengerDao passengerDAO = new PassengerDaoImpl();
        CompanyDao companyDAO = new CompanyDaoImpl();
        Passenger passenger = passengerDAO.getById(500);
        Company company = companyDAO.getById(4);
        Trip trip = new Trip(1, 2333, company, LocalDate.parse("2021-10-10"),
                LocalDate.parse("2021-10-13"), "gg", "hh", passenger);
        passengerDAO.registerTrip(trip, passenger);
        List<Passenger> list = passengerDAO.getPassengersOfTrip(2333);
        passengerDAO.cancelTrip(passenger.getId(), 2333);
        List<Passenger> list1 = passengerDAO.getPassengersOfTrip(2333);
        Assert.assertTrue(list.size() - list1.size() == 1);
    }
}