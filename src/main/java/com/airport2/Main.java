package com.airport2;

import com.airport2.dao.AirCompanyDaoImpl;


public class Main {
    public static void main(String[] args) {

        AirCompanyDaoImpl impl = new AirCompanyDaoImpl();
//        System.out.println("Air Company By ID Is: " +impl.getById(1));
//        System.out.println("All Companies are: "+impl.getAllAirCompanies());


        impl.saveAirCompany(impl.createAirCompany());
        System.out.println(impl.getById(impl.createAirCompany().getId()));

//        System.out.println(impl.update(impl.createAirCompany()));

//        AddressDAOImpl addressDAO = new AddressDAOImpl();
//        ZipCodeDAOImpl zipCodeDAO = new ZipCodeDAOImpl();
//        PassengerDAOImpl passengerDAO = new PassengerDAOImpl();
////        ZipCode zipCode = new ZipCode("0099", "Armenia", "yerevan");
////        Address address = new Address("abovyan", zipCode);
//
//
//            ZipCode zipCode = zipCodeDAO.getById(3);
//            Address address = new Address("abovyan", zipCode);
//            addressDAO.saveAddress(address);
//        List<Address> list = addressDAO.getAllAddresses();
//        System.out.println(list);
////
//        Set<Address> set = addressDAO.get(1,2, "street");
//        System.out.println(set);
////
//        Address address1 = addressDAO.getById(1);
//        address1.setStreet("mashtoc");
//        address1 = addressDAO.update(address);
//        System.out.println(address1);
//        addressDAO.delete(7);
////
////
////
////        create  relation, registration
//        Passenger passenger = new Passenger();
//        passenger = passengerDAO.getById(5);
//        Trip trip = new Trip();
//        TravelCompany travelCompany = new TravelCompany();
//        Connector connector = new Connector();
//        EntityManager entityManager = connector.connectTo();
//        entityManager.getTransaction().begin();
//        trip = entityManager.find(Trip.class, 2);
//        travelCompany = entityManager.find(TravelCompany.class, 1);
//        connector.disconnect();
//        passengerDAO.registerTrip(trip, passenger, travelCompany);
////
//        passengerDAO.cancelTrip(5,2000,1);
////
//
//        passenger.setAddress(address);
//        passenger.setName("Gago");
//        passenger.setPhone("569874566");
//        passengerDAO.savePassenger(passenger);
//
//        Passenger pass = passengerDAO.getById(4);
//
//        pass.setName("Vardan");
//        System.out.println(passengerDAO.update(pass));
//        passengerDAO.delete(pass.getId());
////
//        passengerDAO.getPassengersOfTrip(2000);
//
//
//
     }
}
