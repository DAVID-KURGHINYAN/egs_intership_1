package com.newAirport.controller;


import com.newAirport.entity.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class AirportController {


  private final static String USERNAME = "root";
  private final static String PASSWORD = "admin";
  private final static String URL = "jdbc:mysql://localhost:3306/airport";

  public Map<List<Passenger>, List<Address>> readPassengers() {
    List<Passenger> listOfPassengers = new ArrayList<>();
    List<Address> listOfAddresses = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new
            FileReader("src/main/resources/airport_info/passengers.txt"))) {
      while (bufferedReader.ready()) {
        String pass = bufferedReader.readLine();
        if (pass != null && !pass.endsWith("country,city")) {

          if (pass.contains("'")) {
            pass = pass.replaceAll("'", "’");
          }

          String[] line = pass.split(",");
          Passenger passenger = new Passenger();
          Address address = new Address();
          passenger.setName(line[0]);
          passenger.setPhone(line[1]);

          address.setCountry(line[2]);
          address.setCity(line[3]);
          listOfPassengers.add(passenger);
          listOfAddresses.add(address);
        }
      }
    } catch (FileNotFoundException e) {
      System.err.println("The file you wanted to read was not found");
    } catch (IOException e) {
      System.err.println("A problem occurs, cant read from file, closing the program");
      System.exit(0);
    }
    Map<List<Passenger>, List<Address>> map = new HashMap<>();
    map.put(listOfPassengers, listOfAddresses);
    return map;
  }

  public List<Company> readCompanies() {
    List<Company> listOfCompanies = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(
            new FileReader("src/main/resources/airport_info/companies.txt"))) {
      while (bufferedReader.ready()) {
        String comp = bufferedReader.readLine();
        if (comp != null && !comp.endsWith("date")) {
          String[] line = comp.split(",");
          Company company = new Company();
          company.setName(line[0]);

          String[] dateSplitter = line[1].split("/");

          company.setFoundDate(LocalDate.of(parseInt(dateSplitter[2]),
                  parseInt(dateSplitter[0]), parseInt(dateSplitter[1])));

          listOfCompanies.add(company);
        }
      }
    } catch (FileNotFoundException e) {
      System.err.println("The file you wanted to read was not found");
    } catch (IOException e) {
      System.err.println("Cant read from file, closing the program");
      System.exit(0);
    }
    return listOfCompanies;
  }

  public List<TravelCompany> readTravelCompanies() {
    List<TravelCompany> listOfTravelCompanies = new ArrayList<>();

    try (BufferedReader bufferedReader = new BufferedReader(
            new FileReader("src/main/resources/airport_info/travel_companies.txt"))) {
      while (bufferedReader.ready()) {
        String travelName = bufferedReader.readLine();
        if (travelName != null) {
          TravelCompany travelCompany = new TravelCompany();
          travelCompany.setName(travelName);
          listOfTravelCompanies.add(travelCompany);
        }
      }
    } catch (FileNotFoundException e) {
      System.err.println("The file you wanted to read was not found");
    } catch (IOException e) {
      System.err.println("Cant read from file, closing the program");
      System.exit(0);
    }
    return listOfTravelCompanies;
  }

  public List<Trip> readTrip() {
    List<Company> listOfCompany = new ArrayList<>();
    List<Trip> listOfTrip = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new
            FileReader("src/main/resources/airport_info/trip.txt"))) {
      int i = 0;
      while (bufferedReader.ready()) {

        String pass = bufferedReader.readLine();
        if (!pass.endsWith("from")) {
          String[] line = pass.split(",");
          Trip trip = new Trip();

          trip.setTripNumber(parseInt(line[0]));

          String[] dateSplitter1 = line[1].split("/");
          trip.setTimeIn(LocalDate.of(parseInt(dateSplitter1[2]),
                  parseInt(dateSplitter1[0]), parseInt(dateSplitter1[1])));

          String[] dateSplitter2 = line[2].split("/");
          trip.setTimeOut(LocalDate.of(parseInt(dateSplitter2[2]),
                  parseInt(dateSplitter2[0]), parseInt(dateSplitter2[1])));

          trip.setTownTo(line[3]);
          trip.setTownFrom(line[4]);
          listOfTrip.add(trip);
        }

      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("The file you wanted to read was not found");
    }

    return listOfTrip;
  }

  public void writeToDB() {

    List<Company> listOFCompanies = readCompanies();
    List<Trip> listOfTrip = readTrip();
    List<Passenger> listOfPassengers = new ArrayList<>();
    List<Address> listOfAddresses = new ArrayList<>();
    List<TravelCompany> listOfTravelCompanies = readTravelCompanies();


    Map<List<Passenger>, List<Address>> map = readPassengers();
    for (Map.Entry<List<Passenger>, List<Address>> entry : map.entrySet()) {
      listOfAddresses = entry.getValue();
      listOfPassengers = entry.getKey();
    }
    List<String> tableCreateQueries = new ArrayList<>();




    tableCreateQueries.add(
            "CREATE TABLE IF NOT EXISTS address (id INT AUTO_INCREMENT PRIMARY KEY, postal_code VARCHAR(10)," +
                    " street VARCHAR(10), country VARCHAR(255), city VARCHAR(255))");

    tableCreateQueries.add(
            "CREATE TABLE IF NOT EXISTS company (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), " +
                    "found_date DATE, address_id INT, FOREIGN KEY(address_id) REFERENCES address(id))");

    tableCreateQueries.add(
            "CREATE TABLE IF NOT EXISTS passenger (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), " +
                    "phone VARCHAR(20), address_id INT, FOREIGN KEY(address_id) REFERENCES address(id))");

    tableCreateQueries.add(
            "CREATE TABLE IF NOT EXISTS travel_company " +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), " +
                    "address_id INT, FOREIGN KEY(address_id) REFERENCES address(id))");

    tableCreateQueries.add(
            "CREATE TABLE IF NOT EXISTS trip (id INT AUTO_INCREMENT PRIMARY KEY, trip_number INT, company_id INT, " +
                    " time_in DATE, time_out DATE, town_to VARCHAR(255), town_from VARCHAR(255), " +
                    "FOREIGN KEY(company_id) REFERENCES company(id))");

    tableCreateQueries.add(
            "CREATE TABLE IF NOT EXISTS pass_in_travel_trip " +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, trip_id INT, passenger_id INT, travel_company_id INT, " +
                    " FOREIGN KEY(trip_id) REFERENCES trip(id) , FOREIGN KEY(passenger_id) REFERENCES passenger(id), " +
            " FOREIGN KEY(travel_company_id) REFERENCES travel_company(id))");

    try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
         Statement stmt = conn.createStatement()) {

      for (String sql : tableCreateQueries) {
        stmt.executeUpdate(sql);
      }

      for (Address address : listOfAddresses) {
        stmt.executeUpdate("INSERT INTO address (country, city) VALUES " +
                "('" + address.getCountry() +
                "', '" + address.getCity() + "')");
      }

      for (int i = 0; i < listOFCompanies.size() ; i++) {
        stmt.executeUpdate("INSERT INTO company (name, found_date, address_id) VALUES " +
                "('" + listOFCompanies.get(i).getName() + "', '" +
                listOFCompanies.get(i).getFoundDate() + "', '" + (i+1) + "')");
      }

      for (int i = 0; i < listOfTrip.size(); i++) {
        Company company = listOFCompanies.get(i);
        ResultSet rs = stmt.executeQuery("SELECT id from company where name='" + company.getName() +
                "' AND found_date='" + company.getFoundDate() + "'");
        boolean found = false;
        int company_id = 0;
        while (rs.next() && found == false) {
          found = true;
          company_id = rs.getInt("id");
        }
        stmt.executeUpdate("INSERT INTO trip (trip_number, company_id, time_in, time_out, town_to, town_from) VALUES " +
                "('" + listOfTrip.get(i).getTripNumber() + "', '" + company_id + "', '" +
                listOfTrip.get(i).getTimeIn() + "', '" + listOfTrip.get(i).getTimeOut() + "', '"
                + listOfTrip.get(i).getTownTo() + "', '" + listOfTrip.get(i).getTownFrom() + "')");
      }

      for (int i = 0; i < listOfPassengers.size(); i++) {
        Address address = listOfAddresses.get(i);
        ResultSet rs = stmt.executeQuery("SELECT id from address where country='" + address.getCountry() +
                "' AND city='" + address.getCity() + "'");
        boolean found = false;
        int address_id = 0;
        while (rs.next() && found == false) {
          found = true;
          address_id = rs.getInt("id");
        }
        stmt.executeUpdate("INSERT INTO passenger (name, phone, address_id) VALUES " +
                "('" + listOfPassengers.get(i).getName() + "', '" + listOfPassengers.get(i).getPhone()
                + "', '" + address_id + "')");
      }

      for (int i = 1; i < listOfTravelCompanies.size(); i++) {

        stmt.executeUpdate("INSERT INTO travel_company (name, address_id) VALUES " +
                "('" + listOfTravelCompanies.get(i).getName() + "', '" + (i) + "')");

      }
    } catch (SQLException e) {
      System.err.println("Could not connect to DataBase");
    }
  }
}