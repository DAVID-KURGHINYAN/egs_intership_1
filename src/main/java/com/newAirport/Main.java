package com.newAirport;

import com.newAirport.controller.AirportController;
import com.newAirport.dao.CompanyDaoImpl;
import com.newAirport.dao.TravelCompanyDAOImpl;
import com.newAirport.entity.Company;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        AirportController controller = new AirportController();
        CompanyDaoImpl impl = new CompanyDaoImpl();
        Company company = new Company();
        TravelCompanyDAOImpl travelCompanyDao = new TravelCompanyDAOImpl();

        controller.writeToDB();

/** // Read from file and write to DataBase.
        controller.writeToDB();*/

  // Code for save in DataBase
   /**     company.setName("zzz");
        company.setFoundDate(LocalDate.parse("2000-10-10"));
        System.out.println(impl.save(company));*/

/** // Get Company by id.
        System.out.println(impl.getById(1));
 */
 /** // Update company.
      company.setName("AAA");
        company.setFoundDate(LocalDate.parse("2000-05-05"));
        company.setId(1001);
        System.out.println(impl.update(company));*/

/** // Delete company by id.
        impl.delete(1003);*/

/** // Printing all companies.
        System.out.println(impl.getAll());*/

/** // Printing companies from ....to .... pages.
 *
 */
//        System.out.println(impl.get(4, 100, "name"));

    }
}
