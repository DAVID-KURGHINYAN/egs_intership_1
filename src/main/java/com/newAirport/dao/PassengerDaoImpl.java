package com.newAirport.dao;

import com.newAirport.connector.ConnectToDB;
import com.newAirport.entity.Address;
import com.newAirport.entity.Passenger;
import com.newAirport.entity.Trip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class PassengerDaoImpl implements PassengerDao {
    private final ConnectToDB connectToDB = new ConnectToDB();

    @Override
    public Passenger getById(int id) {
        Passenger passenger = new Passenger();
        String sql = "SELECT * FROM passenger WHERE id=" + id;
        try {
            for (Passenger p : getPassengerFromRs(sql)) {
                passenger = p;
            }
        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        return passenger;
    }

    @Override
    public Set<Passenger> getAll() {
        Set<Passenger> passengers = new HashSet<>();
        String sql = "SELECT * FROM passenger";
        try {
            return getPassengerFromRs(sql);
        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        return passengers;
    }

    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        Set<Passenger> passengers = new LinkedHashSet<>();
        String sql1 = "SELECT * FROM passenger";
        ResultSet rs;
        connectToDB.connect();
        try{
            rs = connectToDB.select(sql1);
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();
            int maxPages = size/perPage;
            if(size % perPage != 0) {
                maxPages++;
            }
            if (page > maxPages){
                System.err.println("No such page index");
                connectToDB.disconnect();
                return passengers;
            }
            int minimalRowIndex = page * perPage - perPage;
            String sql = "SELECT * FROM passenger ORDER BY " + sort + " OFFSET " + minimalRowIndex + " ROWS FETCH NEXT " + perPage + " ROWS ONLY";
            passengers = getPassengerFromRs(sql);
        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        return passengers;
    }

    @Override
    public Passenger save (Passenger passenger) {
        String sql;
        connectToDB.connect();
        if (passenger.getAddress() == null){
            System.err.println("Address cant be empty");
            connectToDB.disconnect();
            return passenger;
        }
        else if (passenger.getAddress().getId() != 0){
            sql = "INSERT INTO passenger (name, phone, address_id) VALUES " +
                    "('" + passenger.getName() + "', '" + passenger.getPhone() + "', '" + passenger.getAddress().getId() + "')";
            connectToDB.createUpdateDelete(sql);
            try {
                ResultSet rs = connectToDB.select("SELECT * from address WHERE id='" + passenger.getAddress().getId() + "'");
                if (!rs.next()){
                    System.err.println("No such address with given Address ID");
                    connectToDB.disconnect();
                    return passenger;
                }
                rs.beforeFirst();
                while(rs.next()) {
                    passenger.getAddress().setCountry(rs.getString("country"));
                    passenger.getAddress().setCity(rs.getString("city"));
                }

                rs = connectToDB.select("SELECT * from passenger WHERE name='" + passenger.getName() + "' AND phone='" +
                        passenger.getPhone() + "' AND address_id='" + passenger.getAddress().getId() + "'");
                rs.next();

                passenger.setId(rs.getInt("id"));
            } catch (SQLException e) {
                System.err.println("Could not connect to DataBase");
            }
        }

        else if (passenger.getAddress().getId() == 0){
            int addressId = 0;
            try {
                ResultSet rs = connectToDB.select("SELECT * from address WHERE country='" + passenger.getAddress().getCountry() + "' AND city='" +
                        passenger.getAddress().getCity() + "'");
                while (rs.next()) {
                    addressId = rs.getInt("id");
                }
            } catch (SQLException e) {
                System.err.println("Could not connect to DataBase");
            }
            if (addressId == 0) {
                String sqlAddress = "INSERT INTO address (country, city) VALUES ('" + passenger.getAddress().getCountry() + "', '" +
                        passenger.getAddress().getCity() + "')";
                connectToDB.createUpdateDelete(sqlAddress);
                ResultSet rs;
                try {
                    rs = connectToDB.select("SELECT * from address WHERE country='" + passenger.getAddress().getCountry() + "' AND city='" +
                            passenger.getAddress().getCity() + "'");
                    rs.next();
                    addressId = rs.getInt("id");
                } catch (SQLException e) {
                    System.err.println("Could not connect to DataBase");
                }

                passenger.getAddress().setId(addressId);
                sql = "INSERT INTO passenger (name, phone, address_id) VALUES " +
                        "('" + passenger.getName() + "', '" + passenger.getPhone() + "', '" + passenger.getAddress().getId() + "')";
                connectToDB.createUpdateDelete(sql);

                try {
                    rs = connectToDB.select("SELECT * from passenger WHERE name='" + passenger.getName() + "' AND phone='" +
                            passenger.getPhone() + "' AND address_id='" + passenger.getAddress().getId() + "'");
                    rs.next();
                    passenger.setId(rs.getInt("id"));
                } catch (SQLException e) {
                    System.err.println("Could not connect to DataBase");
                }
            }

            else  {
                sql = "INSERT INTO passenger (name, phone, address_id) VALUES " +
                        "('" + passenger.getName() + "', '" + passenger.getPhone() + "', '" + addressId + "')";
                connectToDB.createUpdateDelete(sql);
                ResultSet rs;
                try {
                    rs = connectToDB.select("SELECT * from passenger WHERE name='" + passenger.getName() + "' AND phone='" +
                            passenger.getPhone() + "' AND address_id='" + addressId + "'");
                    rs.next();
                    passenger.getAddress().setId(addressId);
                    passenger.setId(rs.getInt("id"));
                } catch (SQLException e) {
                    System.err.println("Could not connect to DataBase");
                }
            }
        }
        connectToDB.disconnect();
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {
        Passenger passenger1 = getById(passenger.getId());
        Address address = new Address();
        connectToDB.connect();
        if (passenger1.getId() == 0 || passenger.getName() == null || passenger.getPhone() == null || passenger.getAddress() == null ||
                (passenger.getAddress() != null && passenger.getAddress().getId() == 0)){
            System.err.println("One or more required fields in passenger are empty");
            connectToDB.disconnect();
            return passenger;
        }
        assert passenger.getAddress() != null;
        int addressID = passenger.getAddress().getId();
        System.out.println(addressID);
        try {
            ResultSet rs = connectToDB.select("SELECT * FROM address WHERE id='" + addressID + "'");
            if (!rs.next()){
                System.err.println("No such Address with address Id");
                connectToDB.disconnect();
                return passenger;
            }
            rs.beforeFirst();
            while(rs.next()) {
                address.setId(rs.getInt("id"));
                address.setCity(rs.getString("city"));
                address.setCountry(rs.getString("country"));
            }
        } catch (SQLException | NullPointerException e) {
            System.err.println("No such Address with address Id");
            connectToDB.disconnect();
            return passenger;
        }

        passenger1.setName(passenger.getName());
        passenger1.setPhone(passenger.getPhone());
        passenger1.setAddress(address);

        connectToDB.createUpdateDelete("UPDATE passenger SET name = '" + passenger1.getName() + "', phone= '"
                + passenger1.getPhone() + "', address_id= '" + passenger1.getAddress().getId() + "' WHERE id = " + passenger1.getId());

        passenger1 = getById(passenger.getId());
        connectToDB.disconnect();
        return passenger1;
    }

    @Override
    public int delete(int passengerId) {
        connectToDB.connect();
        try {
            ResultSet rs = connectToDB.select("SELECT * from passenger WHERE id='" + passengerId + "'");
            if (!rs.next()){
                System.err.println("No such Passenger with given Id, nothing to delete");
            }
            else {
                connectToDB.createUpdateDelete("DELETE FROM passenger WHERE id='" + passengerId + "'");
                System.out.println("Success");
            }
        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        connectToDB.disconnect();
        return passengerId;
    }

    @Override
    public List<Passenger> getPassengersOfTrip(int tripNumber) {
        connectToDB.connect();
        List<Integer> listOfPassengerIds = new ArrayList<>();
        List<Passenger> passengers = new ArrayList<>();
        int tripId = 0;
        try {
            ResultSet rs = connectToDB.select("SELECT * from trip WHERE trip_number='" + tripNumber + "'");
            if (!rs.next()){
                System.err.println("No such Trip with given Trip Number");
                connectToDB.disconnect();
                return passengers;
            }
            rs.beforeFirst();
            while(rs.next()) {
                tripId = rs.getInt("id");
            }

            rs = connectToDB.select("SELECT * from trip_to_passenger_relation WHERE trip_id='" + tripId + "'");
            if (!rs.next()){
                System.err.println("No passengers have a trip with given Trip Number");
                connectToDB.disconnect();
                return passengers;
            }
            rs.beforeFirst();
            while(rs.next()) {
                listOfPassengerIds.add(rs.getInt("passenger_id"));
            }

            for (Integer passId: listOfPassengerIds){
                rs = connectToDB.select("SELECT * from passenger WHERE id='" + passId + "'");
                if (!rs.next()){
                    System.err.println("No such Passenger with given Trip Number");
                    continue;
                }
                rs.beforeFirst();
                while(rs.next()) {
                    Passenger passenger = new Passenger();
                    Address address = new Address();
                    passenger.setId(rs.getInt("id"));
                    passenger.setName(rs.getString("name"));
                    passenger.setPhone(rs.getString("phone"));
                    int addressId = rs.getInt("address_id");
                    rs = connectToDB.select("SELECT * from address WHERE id='" + addressId + "'");
                    if (!rs.next()){
                        System.err.println("No such Address with given with given passID");
                        continue;
                    }
                    rs.beforeFirst();
                    while(rs.next()) {
                        address.setCity(rs.getString("city"));
                        address.setCountry(rs.getString("country"));
                        address.setId(addressId);
                    }
                    passenger.setAddress(address);
                    passengers.add(passenger);
                }
            }
        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        connectToDB.disconnect();
        return passengers;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {
        connectToDB.connect();
        connectToDB.createUpdateDelete("INSERT INTO trip_to_passenger_relation (trip_id, passenger_id) VALUES ('" +
                trip.getId() + "', '" + passenger.getId() + "')");
        connectToDB.disconnect();
    }

    @Override
    public void cancelTrip(int passengerId, int tripNumber) {
        connectToDB.connect();
        int tripId = 0;
        try {
            ResultSet rs = connectToDB.select("SELECT * FROM trip WHERE trip_number='" + tripNumber + "'");
            if (!rs.next()){
                System.err.println("No such Trip with given Trip Number");
                connectToDB.disconnect();
                return;
            }
            rs.beforeFirst();
            while(rs.next()) {
                tripId = rs.getInt("id");
            }
            connectToDB.createUpdateDelete("DELETE FROM trip_to_passenger_relation WHERE trip_id='" + tripId +
                    "' AND passenger_id='" + passengerId + "'");
        } catch (SQLException e) {
            System.err.println("Could not connect to DataBase");
        }
        connectToDB.disconnect();
    }

    public Set<Passenger> getPassengerFromRs(String sql) throws SQLException {
        ResultSet rs;
        connectToDB.connect();
        Set<Passenger> passengers = new LinkedHashSet<>();
        rs = connectToDB.select(sql);
        while(rs.next()) {
            Passenger passenger = new Passenger();
            passenger.setName(rs.getString("name"));
            passenger.setId(rs.getInt("id"));
            passenger.setPhone(rs.getString("phone"));
            int addressId = rs.getInt("address_id");
            String sqlAddress = "SELECT * FROM address WHERE id=" + addressId;
            passenger.setAddress(getAddressFromRS(sqlAddress));
            passengers.add(passenger);
        }
        connectToDB.disconnect();
        return passengers;
    }


    public Address getAddressFromRS(String sql) throws SQLException {
        Address address = new Address();
        ResultSet rs = connectToDB.select(sql);
        while(rs.next()) {
            address.setCity(rs.getString("city"));
            address.setCountry(rs.getString("country"));
            address.setId(rs.getInt("id"));
        }
        return address;
    }
}