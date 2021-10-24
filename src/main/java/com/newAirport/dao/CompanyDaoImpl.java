package com.newAirport.dao;

import com.newAirport.connector.ConnectToDB;
import com.newAirport.entity.Address;
import com.newAirport.entity.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


public class CompanyDaoImpl implements CompanyDao {
    ConnectToDB connectToDB = new ConnectToDB();


    @Override
    public Company save(Company company) {
        connectToDB.connect();

            connectToDB.createUpdateDelete("INSERT INTO company (name, found_date) VALUES " +
                    "('"+company.getName()+"','"+company.getFoundDate()+"')");

        connectToDB.disconnect();
        return company;
    }

    @Override
    public Company getById(int id) {
        Company company = new Company();
        String sql = "SELECT * FROM company WHERE id ="+ id;
        try {
            for (Company c : getCompanyFromRs(sql)) {
                company = c;
            }

        }catch (SQLException e){
            System.err.println("Your data is incorrect");
        }
        return company;
    }

    @Override
    public Company update(Company company) {

        try {

            PreparedStatement ps = connectToDB.connect().prepareStatement
                    ("UPDATE company SET name = ?, found_date = ?, address_id = ? WHERE id = "+ company.getId());

            // set the preparedStatement parameters

            ps.setString(1, company.getName());
            ps.setString(2, String.valueOf(company.getFoundDate()));
            ps.setInt(3,company.getAddress().getId());


            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
            System.out.println("Success");
            ps.close();

        }catch (SQLException e){
            System.err.println("Your data is incorrect");
        }
        connectToDB.disconnect();
        return company;
    }

    @Override
    public int delete(int id) {
        int success = -1;
        try {
            PreparedStatement st = connectToDB.connect()
                    .prepareStatement("DELETE FROM company WHERE id = " + id );
            st.executeUpdate();
            success = 0;
            return success;
        } catch (SQLException e) {
            System.err.println("Your data is incorrect");
        }
        System.out.println("The company was deleted");
        connectToDB.disconnect();
        return success;
    }

    /**
     * This method return us Set of all Companies.
     * */
    @Override
    public Set<Company> getAll() {
        Set<Company> companies = new HashSet<>();
        String sql = "SELECT * FROM company";

        try {
            companies.addAll(getCompanyFromRs(sql));
        }catch (SQLException e){
            System.err.println("Your data is incorrect");
        }
        connectToDB.disconnect();
        return companies;
    }

    /**
     * This method return us Set of all Companies,
     * where we can sort by id, by name or by found_date.
     * */
    @Override
    public Set<Company> get(int page, int perPage, String sort) {
        Set<Company> companies = new LinkedHashSet<>();
        String sql = "SELECT * FROM company order by " + sort+
                " LIMIT "+ perPage+ " OFFSET " +((page-1)*perPage+1);
        try {
            companies.addAll(getCompanyFromRs(sql));
        }catch (SQLException e) {
            System.err.println("Your data is incorrect");
        }
        return companies;
    }

    /**
     * This method return us Set of Companies with id, name and found date from DataBase.
     * */
    public Set<Company> getCompanyFromRs(String sql) throws SQLException {
        ResultSet rs;
        connectToDB.connect();
        Set<Company> companies = new LinkedHashSet<>();
        rs = connectToDB.select(sql);
        while(rs.next()) {
            Company company = new Company();
            company.setId(rs.getInt("id"));
            company.setName(rs.getString("name"));
            company.setFoundDate(LocalDate.parse(rs.getString("found_date")));
            Address address;
            String sql1 = "SELECT * FROM address WHERE id = " + company.getId();
            address = getAddressFromRS(sql1);
            company.setAddress(address);
            companies.add(company);
        }
        connectToDB.disconnect();
        return companies;
    }
    public Address getAddressFromRS(String sql) throws SQLException {
        Address address = new Address();
        ResultSet rs = connectToDB.select(sql);
        while(rs.next()) {
            address.setStreet(rs.getString("street"));
            address.setPostalCode(rs.getString("postal_code"));
            address.setCity(rs.getString("city"));
            address.setCountry(rs.getString("country"));
            address.setId(rs.getInt("id"));
        }
        return address;
    }
}
