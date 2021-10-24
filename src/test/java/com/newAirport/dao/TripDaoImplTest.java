package com.newAirport.dao;

import com.newAirport.entity.Address;
import com.newAirport.entity.Company;
import com.newAirport.entity.Passenger;
import com.newAirport.entity.Trip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TripDaoImplTest {

    @Test
    public void getByIdAndSave() {
        TripDao tripDAO = new TripDaoImpl();
        Address address = new Address();
        address.setId(2);
        address.setCountry("Armenia");
        address.setCity("Yerevan");
        Company company = new Company(1, "Oyondu", address, LocalDate.parse("1988-01-28"));
        Trip trip = new Trip(6899, company, LocalDate.parse("2020-10-11"), LocalDate.parse("2020-10-12"),
                "aaa", "bbb");
        Trip trip1 = tripDAO.save(trip);
        Trip trip2 = tripDAO.getById(trip1.getId());
        Assertions.assertEquals(trip1.getId(), trip2.getId());
        Assertions.assertEquals(trip1.getTripNumber(), trip2.getTripNumber());
        Assertions.assertEquals(trip1.getCompany().getId(), trip2.getCompany().getId());
        Assertions.assertEquals(trip1.getTimeIn(), trip2.getTimeIn());
        Assertions.assertEquals(trip1.getTimeOut(), trip2.getTimeOut());
        Assertions.assertEquals(trip1.getTownFrom(), trip2.getTownFrom());
        Assertions.assertEquals(trip1.getTownTo(), trip2.getTownTo());
    }

    @Test
    public void getAll() {
        TripDao tripDAO = new TripDaoImpl();
        int tripSize1 = tripDAO.getAll().size();
        Address address = new Address();
        address.setId(1);
        address.setCountry("Armenia");
        address.setCity("Yerevan");

        Company company = new Company(1, "Oyondu", address, LocalDate.parse("1988-01-28"));
        Trip trip = new Trip(6899, company, LocalDate.parse("2020-10-11"), LocalDate.parse("2020-10-12"),
                "aaa", "bbb");

        tripDAO.save(trip);

        assertEquals(tripSize1, tripDAO.getAll().size() - 1);
    }

    @Test
    public void getByPage() {
        TripDao tripDAO = new TripDaoImpl();
        Set<Trip> trips;
        int numberTrip = 0;
        trips = tripDAO.getByPage(1, 20, "trip_number");
        for (Trip trip : trips) {
            if (numberTrip != 0) {
                Assertions.assertTrue(numberTrip <= trip.getTripNumber());
            }
            numberTrip = trip.getTripNumber();

        }
        assertTrue(trips.size() <= 20);
    }

    @Test
    public void save() {
        TripDao tripDAO = new TripDaoImpl();
        Address address = new Address();
        address.setId(1);
        address.setCountry("Armenia");
        address.setCity("Yerevan");
        Company company = new Company(1, "Oyondu", address, LocalDate.parse("1988-01-28"));
        Passenger passenger = new Passenger("John", "Snow", address);
        Trip trip = new Trip(6666, company, LocalDate.parse("2020-10-11"), LocalDate.parse("2020-10-12"),
                "aaa", "bbb");
        Trip trip1 = tripDAO.save(trip);
        Trip trip2 = tripDAO.getById(trip1.getId());
        System.out.println(passenger);
        Assertions.assertEquals(trip1.getId(), trip2.getId());
        Assertions.assertEquals(trip1.getTripNumber(), trip2.getTripNumber());
        Assertions.assertEquals(trip1.getCompany().getId(), trip2.getCompany().getId());
        Assertions.assertEquals(trip1.getTimeIn(), trip2.getTimeIn());
        Assertions.assertEquals(trip1.getTimeOut(), trip2.getTimeOut());
        Assertions.assertEquals(trip1.getTownFrom(), trip2.getTownFrom());
        Assertions.assertEquals(trip1.getTownTo(), trip2.getTownTo());
    }

    @Test
    public void update() {
        TripDao tripDao = new TripDaoImpl();
        Address address = new Address();
        address.setId(1);
        address.setCountry("Armenia");
        address.setCity("Yerevan");
        Company company = new Company(1, "Oyondu", address, LocalDate.parse("1988-01-28"));
        Passenger passenger = new Passenger("John", "Snow", address);
        Trip trip = new Trip(2, 6666, company, LocalDate.parse("2020-10-11"), LocalDate.parse("2020-10-12"),
                "aaa", "bbb", passenger);
        tripDao.update(trip);
        Trip trip1 = tripDao.getById(2);
        Assertions.assertEquals(trip.getId(), trip1.getId());
        Assertions.assertEquals(trip.getTripNumber(), trip1.getTripNumber());
        Assertions.assertEquals(trip.getCompany().getId(), trip1.getCompany().getId());
        Assertions.assertEquals(trip.getTimeIn(), trip1.getTimeIn());
        Assertions.assertEquals(trip.getTimeOut(), trip1.getTimeOut());
        Assertions.assertEquals(trip.getTownFrom(), trip1.getTownFrom());
        Assertions.assertEquals(trip.getTownTo(), trip1.getTownTo());
    }

    @Test
    public void delete() {
        TripDao tripDAO = new TripDaoImpl();
        if (tripDAO.delete(26) != 0)
            assertNull(tripDAO.getById(26));
    }

    @Test
    public void getTripsFrom() {

        TripDao tripDAO = new TripDaoImpl();
        Address address = new Address();
        address.setId(1);
        address.setCountry("Armenia");
        address.setCity("Yerevan");
        Company company = new Company(1, "Oyondu", address, LocalDate.parse("1988-01-28"));
        Passenger passenger = new Passenger("John", "Snow", address);
        Trip trip = new Trip(6666, company, LocalDate.parse("2020-10-11"), LocalDate.parse("2020-10-12"),
                "Kasreman", "Yerevan");
        Trip trip1 = tripDAO.save(trip);
        Trip trip2 = tripDAO.getById(trip1.getId());
        System.out.println(passenger);
        assertEquals(trip2.getTownFrom(), "Yerevan");
    }

    @Test
    public void getTripsTo() {
        TripDao tripDAO = new TripDaoImpl();
        Address address = new Address();
        address.setId(1);
        address.setCountry("Armenia");
        address.setCity("Yerevan");
        Company company = new Company(1, "Oyondu", address, LocalDate.parse("1988-01-28"));
        Passenger passenger = new Passenger("John", "Snow", address);
        Trip trip = new Trip(6666, company, LocalDate.parse("2020-10-11"), LocalDate.parse("2020-10-12"),
                "Yerevan", "Castelo");
        Trip trip1 = tripDAO.save(trip);
        Trip trip2 = tripDAO.getById(trip1.getId());
        assertEquals(trip2.getTownTo(), "Yerevan");
        System.out.println(passenger);
    }
}