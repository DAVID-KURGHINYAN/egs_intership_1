package com.newAirport.dao;

import com.newAirport.connector.ConnectToDB;
import com.newAirport.entity.Address;
import com.newAirport.entity.TravelCompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

class TravelCompanyTest {
    ConnectToDB connectToDB = new ConnectToDB();
    TravelCompany travelCompany = new TravelCompany();
    ResultSet rs ;
    TravelCompanyDAOImpl impl = new TravelCompanyDAOImpl();

    @Test
    public void connect() {
        Connection connection = connectToDB.connect();
        Assertions.assertNotNull(connection);
        connectToDB.disconnect();
    }

    @Test
    void getById() {
        travelCompany.setId(1);
        travelCompany.setName("Armenia Travel");

        String sql = "SELECT * FROM travel_company WHERE id ="+ travelCompany.getId();
        TravelCompany com = new TravelCompany();
        connectToDB.connect();
        try {
            rs = connectToDB.select(sql);
            while (rs.next()){

                com.setId(rs.getInt("id"));
                com.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(travelCompany.getName().length(), com.getName().length());
        Assertions.assertEquals(travelCompany.getId(),com.getId());
    }

    @Test
    void getAll() throws SQLException {
        String sql = "SELECT * FROM travel_company";
        int size = 0;
        connectToDB.connect();
        try {
            rs = connectToDB.select(sql);
            rs.last();
            size = rs.getRow();
            connectToDB.disconnect();
        }catch (SQLException e) {
            System.err.println("Your data is incorrect");
        }
        Assertions.assertEquals(size, impl.getAll().size());

    }

    @Test
    void getByPage() {

        Set<TravelCompany> set = impl.getByPage(2, 20, "name");
        String name = "";
        for (TravelCompany tc: set){
            if (!name.equals("")){
                int comp = name.compareTo(tc.getName());
                Assertions.assertTrue(comp <= 0);
            }
            name = tc.getName();
        }
        Assertions.assertTrue(set.size() <= 20);
    }


    @Test
    void deleteAndSave() {
        Address address = new Address("Armenia", "Yerevan");
        TravelCompany tc = new TravelCompany(101,"John Snow", address);
        TravelCompany travelCom  = impl.save(tc);

        int idOfSavedTravelCompany = travelCom.getId();
        System.out.println(travelCom);

        Assertions.assertEquals(idOfSavedTravelCompany, tc.getId());
        int i = impl.delete(idOfSavedTravelCompany);

        Assertions.assertNotEquals(tc.getId(), i);
    }

    @Test
    void update() throws SQLException {
        Address address = new Address();
        address.setId(1);
        address.setCountry("Armenia");
        address.setCity("Yerevan");
        TravelCompany company = new TravelCompany(11, "Destination Vacation", address);
        impl.update(company);
        TravelCompany tc = impl.getById(12);
        Assertions.assertEquals(company.getId(), tc.getId()-1);
        Assertions.assertEquals(company.getName(),tc.getName());
    }
}