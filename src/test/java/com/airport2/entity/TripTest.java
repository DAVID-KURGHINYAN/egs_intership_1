package com.airport2.entity;

import com.airport2.dao.TripDao;
import com.airport2.dao.TripDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class TripTest {

    @Test
    void saveAndDelete() {
        List<Passenger> passengerList =new ArrayList<>();
        Trip trip = new Trip();

        TripDao tripDao =new TripDaoImpl();
        AirCompany airCompany=new AirCompany();
        Address address=new Address();
        ZipCode zipCode=new ZipCode();
        zipCode.setCity("Yerevan");
        zipCode.setCountry("Armenia");
        zipCode.setPostalCode("0025");
        address.setStreet("Tumanyan");
        address.setZipCode(zipCode);
        airCompany.setAddress(address);
        airCompany.setFoundDate(LocalDate.parse("2000-10-10"));
        airCompany.setName("as");
        trip.setAirCompany(airCompany);
        trip.setTripNumber(1);
        trip.setPassengers(passengerList);
        trip.setTimeIn(LocalDate.parse("2020-10-10"));
        trip.setTownFrom("Yerevan");
        trip.setTownTo("Artik");
        trip.setTimeOut(LocalDate.parse("2020-10-11"));
        tripDao.save(trip);
        int id=trip.getId();
        Assertions.assertNotEquals(trip, null);
        tripDao.delete(id)
        ;
        Assertions.assertNotEquals(0,id);
    }

    @Test
    void update() {
        List<Passenger> passengerList =new ArrayList<>();
        Trip trip=new Trip();
        Trip trip1;

        TripDao tripDao =new TripDaoImpl();
        AirCompany airCompany=new AirCompany();
        Address address=new Address();
        ZipCode zipCode=new ZipCode();
        zipCode.setCity("Artik");
        zipCode.setCountry("Armenia");
        zipCode.setPostalCode("0027");
        address.setStreet("Abovyan");
        address.setZipCode(zipCode);
        airCompany.setAddress(address);
        airCompany.setFoundDate(LocalDate.parse("2000-10-05"));
        airCompany.setName("as");
        trip.setAirCompany(airCompany);
        trip.setTripNumber(1);
        trip.setPassengers(passengerList);
        trip.setTimeIn(LocalDate.parse("2020-10-06"));
        trip.setTownFrom("Yerevan");
        trip.setTownTo("Artik");
        trip.setTimeOut(LocalDate.parse("2020-10-11"));

        trip1= tripDao.getById(1);
        tripDao.update(trip1);
        Assertions.assertEquals(trip,trip1);
    }

    @Test
    void getTripsFrom() {
        List<Trip> list=new ArrayList<>();
        TripDaoImpl tripDAO=new TripDaoImpl();
        list.addAll(tripDAO.getTripsFrom("Yerevan"));
        Trip trip=new Trip();
        trip.setTownFrom("Yerevan");
        Assertions.assertEquals(list.get(1).getTownFrom(),"Yerevan");


    }

    @Test
    void getTripsTo() {
        List<Trip> list=new ArrayList<>();
        TripDaoImpl tripDAO=new TripDaoImpl();
        list.addAll(tripDAO.getTripsTo("Yerevan"));
        Trip trip=new Trip();
        trip.setTownTo("Yerevan");
        Assertions.assertEquals(list.get(1).getTownTo(),"Yerevan");

    }

    @Test
    void getById() {
        Trip trip=new Trip();
        trip.setId(2);
        TripDaoImpl tripDAO=new TripDaoImpl();
        Trip trip1= tripDAO.getById(trip.getId());
        Assertions.assertEquals(trip1.getId(),trip.getId());
    }

    @Test
    void getAll() {
        TripDaoImpl tripDAO =new TripDaoImpl();
        List<Trip> tripList =new ArrayList<>();
        tripList.addAll(tripDAO.getAll());
        Trip trip =tripDAO.getById(1);
        Assertions.assertEquals(tripList.get(0).getId(),trip.getId());

    }
}