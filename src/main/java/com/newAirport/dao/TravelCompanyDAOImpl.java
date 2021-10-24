package com.newAirport.dao;

import com.newAirport.connector.ConnectToDB;
import com.newAirport.entity.Address;
import com.newAirport.entity.TravelCompany;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class TravelCompanyDAOImpl implements TravelCompanyDAO{

    ConnectToDB connectToDB = new ConnectToDB();

    @Override
    public TravelCompany getById(int id) throws SQLException {
        TravelCompany travelCompany = new TravelCompany();
        String sql = "SELECT * FROM travel_company WHERE id =" + id;
        try {
            for (TravelCompany c : getTravelFromRs(sql)) {
                travelCompany = c;
            }

        } catch (SQLException e) {
            System.err.println("Your data is incorrect");
        }
        return travelCompany;
    }

    @Override
    public Set getAll() throws SQLException {
        Set<TravelCompany> travelCompanies = new HashSet<>();
        String sql = "SELECT * FROM travel_company";

        try {
            travelCompanies.addAll(getTravelFromRs(sql));
        } catch (SQLException e) {
            System.err.println("Your data is incorrect");
        }
        connectToDB.disconnect();
        return travelCompanies;
    }

    @Override
    public Set<TravelCompany> getByPage(int page, int perPage, String sort) {
        Set<TravelCompany> travelCompanies = new LinkedHashSet<>();
        String sql = "SELECT * FROM travel_company order by " + sort +
                " LIMIT " + perPage + " OFFSET " + (((perPage - 1) * page + 1));
        try {
            travelCompanies.addAll(getTravelFromRs(sql));
        } catch (SQLException e) {
            System.err.println("Your data is incorrect");
        }
        return travelCompanies;
    }

    @Override
    public int delete(int id) {
        int success = -1;
        try {
            PreparedStatement st = connectToDB.connect()
                    .prepareStatement("DELETE FROM travel_company WHERE id = " + id);
            st.executeUpdate();
            success = 0;
            return success;
        } catch (SQLException e) {
            System.err.println("Your data is incorrect");
        }
        System.out.println("The travel company was deleted");
        connectToDB.disconnect();
        return success;
    }

    @Override
    public TravelCompany save(TravelCompany travel) {
        connectToDB.connect();

        connectToDB.createUpdateDelete("INSERT INTO travel_company (name) VALUES " +
                "('" + travel.getName() + "','" + travel.getAddress() + "')");

        connectToDB.disconnect();
        return travel;
    }

    @Override
    public TravelCompany update(TravelCompany travel) {
        TravelCompany newTravel = new TravelCompany();

        int id = travel.getId();

        try {
            PreparedStatement ps = connectToDB.connect().prepareStatement
                    ("UPDATE travel_company SET name = ? WHERE id = " + id);

            // set the preparedStatement parameters

            ps.setString(1, travel.getName());

            // call executeUpdate to execute our sql update statement

            ps.executeUpdate();
            System.out.println("Success");
            ps.close();

        } catch (SQLException e) {
            System.err.println("Your data is incorrect");
        }
        connectToDB.disconnect();
        return travel;
    }

    public Set<TravelCompany> getTravelFromRs(String sql) throws SQLException {

        ResultSet rs;
        connectToDB.connect();
        Set<TravelCompany> travelCompanies = new LinkedHashSet<>();
        rs = connectToDB.select(sql);
        while (rs.next()) {
            TravelCompany travelCompany = new TravelCompany();
            travelCompany.setId(rs.getInt("id"));
            travelCompany.setName(rs.getString("name"));
            int addressId = rs.getInt("address_id");
            String sqlAddress = "SELECT * FROM address WHERE id=" + addressId;
            travelCompany.setAddress(getAddressFromRS(sqlAddress));

            travelCompanies.add(travelCompany);
        }
        connectToDB.disconnect();
        return travelCompanies;
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
