package com.newAirport.dao;

import com.newAirport.connector.ConnectToDB;
import com.newAirport.entity.Company;
import com.newAirport.entity.Trip;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TripDaoImpl implements TripDao {

    private final ConnectToDB connectToDB = new ConnectToDB();

    @Override
    public Trip getById(int id) {
        Set<Trip> trips;
        Trip trip = new Trip();
        try {
            connectToDB.connect();
            String sql = "SELECT * FROM trip WHERE id=" + id;
            connectToDB.select(sql);
            trips = getTripFromRs(sql);
            if (trips.size() != 0) {
                for (Trip t : trips) {
                    trip = t;
                }
            } else {
                return null;
            }
        } catch (SQLException | IndexOutOfBoundsException e) {
            System.err.println("Could not connect to DataBase");
        }
        connectToDB.disconnect();
        return trip;
    }

    @Override
    public Set<Trip> getAll() {
        Set<Trip> trips = new LinkedHashSet<>();
        String sql = "SELECT * FROM trip";
        try {
            trips = getTripFromRs(sql);
        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        return trips;
    }

    @Override
    public Set<Trip> getByPage(int page, int perPage, String sort) {
        Set<Trip> trips = new LinkedHashSet<>();

        connectToDB.connect();
        try {
            String sql = "SELECT * FROM trip order by " + sort + " LIMIT " + perPage +
                    " OFFSET " + ((perPage - 1) * page + 1);
            if (getTripFromRs(sql) != null) {
                trips = getTripFromRs(sql);
            } else return null;
        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        connectToDB.disconnect();
        return trips;
    }

    @Override
    public Trip save(Trip trip) {

        Connection connection = connectToDB.connect();

        try {
            if (trip != null) {
                String query = "INSERT INTO trip (trip_number, company_id, town_to, town_from, time_in, time_out) VALUES (?, ?, ?, ?, ?, ?) ";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, trip.getTripNumber());
                preparedStatement.setInt(2, trip.getCompany().getId());
                preparedStatement.setString(3, trip.getTownTo());
                preparedStatement.setString(4, trip.getTownFrom());
                preparedStatement.setDate(5, Date.valueOf(trip.getTimeIn()));
                preparedStatement.setDate(6, Date.valueOf(trip.getTimeOut()));
                preparedStatement.execute();
                System.out.println("Trip with trip number =" + trip.getTripNumber() + " is saved.");
            } else System.out.println("The trip was not registered because the required data is missing.");

        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        ResultSet rs;
        try {
            rs = connectToDB.select("SELECT * FROM trip");
            if (rs.last()) {
                assert trip != null;
                trip.setId(rs.getInt("id"));
            }
        } catch (SQLException| NullPointerException e) {
            System.err.println("Could not connect to DataBase");
        }
        connectToDB.disconnect();
        return trip;
    }

    @Override
    public Trip update(Trip trip) {
        connectToDB.connect();
        try {
            ResultSet rs = connectToDB.select("SELECT * FROM trip WHERE id='" + trip.getId() + "'");
            if (!rs.next()) {
                System.err.println("No such trip with given Id, nothing to update");
            } else {
                connectToDB.createUpdateDelete("UPDATE trip SET trip_number = '" + trip.getTripNumber() +
                        "', company_id= '" + trip.getCompany().getId() + "', time_in= '" + trip.getTimeIn() +
                        "', time_out= '" + trip.getTimeOut() + "', town_to= '" + trip.getTownTo() + "', town_from= '" +
                        trip.getTownFrom() + "' WHERE id = " + trip.getId());
                System.out.println("Success");
            }
        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        connectToDB.disconnect();
        return trip;
    }

    @Override
    public int delete(int tripId) {
        connectToDB.connect();
        try {connectToDB.connect();
            ResultSet rs = connectToDB.select("SELECT * FROM trip WHERE id='" + tripId + "'");
            if (!rs.next()) {
                System.err.println("No such trip with given Id, nothing to delete");
                return  0;
            } else {
                connectToDB.createUpdateDelete("DELETE FROM trip WHERE id='" + tripId + "'");
                System.out.println("Success delete");
            }
        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        connectToDB.disconnect();
        return tripId;
    }

    @Override
    public List<Trip> getTripsFrom(String townFrom) {
        List<Trip> trips = new ArrayList<>();
        String sql = "SELECT * FROM trip WHERE town_from='" + townFrom + "'";
        try {
            trips.addAll(getTripFromRs(sql));
            if (trips.size() == 0) {
                System.out.println("There are no trips from " + townFrom);
            } else return null;
        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        connectToDB.connect();

        return trips;
    }

    @Override
    public List<Trip> getTripsTo(String townTo) {
        List<Trip> trips = new ArrayList<>();
        String sql = "SELECT * FROM trip WHERE town_to='" + townTo + "'";
        try {
            trips.addAll(getTripFromRs(sql));
            if (trips.size() == 0) {
                System.out.println("There are no trips to " + townTo);
            } else return null;
        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        return trips;
    }

    public Set<Trip> getTripFromRs(String sql) throws SQLException {
        ResultSet rs;
        Set<Trip> trips = new LinkedHashSet<>();
        connectToDB.connect();
        rs = connectToDB.select(sql);
        while (rs.next()) {
            Trip trip = new Trip();
            trip.setId(rs.getInt("id"));
            trip.setTripNumber(rs.getInt("trip_number"));
            trip.setTimeIn(LocalDate.parse(rs.getString("time_in")));
            trip.setTimeOut(LocalDate.parse(rs.getString("time_out")));
            trip.setTownFrom(rs.getString("town_from"));
            trip.setTownTo(rs.getString("town_to"));
            int companyId = rs.getInt("company_id");

            String sqlCompany = "SELECT * FROM company WHERE id =" + companyId;
            trip.setCompany(getCompanyFromRs(sqlCompany));
            trips.add(trip);
        }
        connectToDB.disconnect();
        return trips;
    }

    public Company getCompanyFromRs(String sql) throws SQLException {
        Company company = new Company();
        ResultSet rs = connectToDB.select(sql);
        if (rs.next()) {
            company.setId(rs.getInt("id"));
            company.setName(rs.getString("name"));
            company.setFoundDate(LocalDate.parse(rs.getString("found_date")));
        }
        return company;
    }
}

