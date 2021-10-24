package com.newAirport.dao;

import com.newAirport.connector.ConnectToDB;
import com.newAirport.entity.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class CompanyTest {
    ConnectToDB connectToDB = new ConnectToDB();
    CompanyDao companyDao = new CompanyDaoImpl();
    Company company = new Company();
    CompanyDaoImpl impl = new CompanyDaoImpl();

    @Test
    public void connect() {
        Connection connection = connectToDB.connect();
        Assertions.assertNotNull(connection);
        connectToDB.disconnect();
    }
    

    @Test
    void saveAndDelete() {
        company.setId(5000);
        company.setName("ABC");
        company.setFoundDate(LocalDate.parse("2000-10-10"));
        Company saveCompany = companyDao.save(company);
        int idOfSavedCompany = saveCompany.getId();
        int i = companyDao.delete(idOfSavedCompany);

        Assertions.assertEquals(5000, saveCompany.getId());
        Assertions.assertEquals(0, i);


    }

    @Test
    void getById() {
        company.setId(1);
        company.setName("Oyondu");
        company.setFoundDate(LocalDate.parse("1988-01-28"));
        Assertions.assertEquals(company.getName(),companyDao.getById(company.getId()).getName());
        Assertions.assertEquals(company.getId(),companyDao.getById(company.getId()).getId());
        Assertions.assertEquals(company.getFoundDate(),companyDao.getById(company.getId()).getFoundDate());
        Assertions.assertEquals(company.getAddress().getCity(),companyDao.getById(company.getId()).getAddress().getCity());
        Assertions.assertEquals(company.getAddress().getCountry().length(),
                companyDao.getById(company.getId()).getAddress().getCountry().length());
    }

    @Test
    void update() {
        Company company = companyDao.getById(1);
        company.setId(2);
        companyDao.update(company);
        Company company1 = companyDao.getById(2);
        Assertions.assertEquals(company.getName(),company1.getName());
        Assertions.assertEquals(company.getFoundDate(),company1.getFoundDate());
        Assertions.assertEquals(company.getAddress().getStreet(),company1.getAddress().getStreet());

    }

    @Test
    void getAll() {
       connectToDB.connect();
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM company";

        try {
            companies.addAll(impl.getCompanyFromRs(sql));
        }catch (SQLException e){
            System.err.println("Your data is incorrect");
        }
        connectToDB.disconnect();

        Assertions.assertEquals(companies.size(),companyDao.getAll().size());
       Assertions.assertEquals(companies.get(0).getName(),"Oyondu");
    }

    @Test
    void get() {
        String sort = "name";
        int perPage = 100;
        int page = 2;
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM company order by " + sort+
                " LIMIT "+ perPage+ " OFFSET " +((page+1)*perPage-1);
        try {
            companies.addAll(impl.getCompanyFromRs(sql));
        }catch (SQLException e) {
            System.err.println("Your data is incorrect");
        }
        Assertions.assertEquals(companies.get(99).getName(),impl.getById(170).getName());
        Assertions.assertEquals(companies.get(99).getId(),impl.getById(170).getId());
        Assertions.assertEquals(companies.get(0).getName(), impl.getById(493).getName());

        /** compareToIgnoreCase() method returns a negative integer,
         *  zero, or a positive integer as the specified String is greater than,
         *  equal to, or less than this String, ignoring case considerations.
         *  */

        Assertions.assertEquals(companies.size(),perPage);
        Assertions.assertEquals(companies.get(0).getName().compareToIgnoreCase("GABSPOT"),0);

    }
}