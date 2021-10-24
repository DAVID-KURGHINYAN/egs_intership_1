package com.airport2.dao;

import com.airport2.EMF;
import com.airport2.entity.Passenger;
import com.airport2.entity.TravelCompany;
import com.airport2.entity.Trip;
import com.airport2.entity.TripToPassengerToTravelCompanyRel;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PassengerDaoImpl implements PassengerDao {

EntityManager em = EMF.getEntityManager();

    @Override
    public Passenger getById(int id) {
        em.getTransaction().begin();
        Passenger passenger = em.find(Passenger.class, id);
        em.getTransaction().commit();
        return passenger;
    }

    @Override
    public List<Passenger> getAllPassengers() {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT a FROM Address a");
        List<Passenger> list = (List<Passenger>) query.getResultList();
        em.getTransaction().commit();
        return list;
    }

    @Override
    public Set<Passenger> get(int page, int perPage, String sort) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT a FROM Passenger a ORDER BY " + sort);
        query.setFirstResult((page - 1) * perPage);
        query.setMaxResults(perPage);
        List<Passenger> list = query.getResultList();
        Set<Passenger> set = new LinkedHashSet<>(list);
        em.getTransaction().commit();
        return set;
    }

    @Override
    public void savePassenger(Passenger passenger) {
        em.getTransaction().begin();
        em.persist(passenger);
        em.getTransaction().commit();
    }

    @Override
    public Passenger update(Passenger passenger) {
        int passengerId = passenger.getId();
        em.getTransaction().begin();
        Session session = (Session) em.getDelegate();
        session.update(passenger);
        passenger = session.find(Passenger.class, passengerId);
        em.getTransaction().commit();
        return passenger;
    }

    @Override
    public void delete(int passengerId) {
        em.getTransaction().begin();
        Passenger passenger = getById(passengerId);
        em.remove(em.contains(passenger) ? passenger : em.merge(passenger));
        em.getTransaction().commit();
    }

    @Override
    public List<Passenger> getPassengersOfTrip(int tripNumber) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT t FROM Trip t WHERE tripNumber= " + tripNumber);
        Trip trip = (Trip) query.getSingleResult();
        List<Passenger> passengersList = new ArrayList<>();
        for (TripToPassengerToTravelCompanyRel t: trip.getTripToPassengerToTravelCompanyRels()){
            passengersList.add(t.getPassenger());
        }
        em.getTransaction().commit();
        return passengersList;
    }


    @Override
    public void registerTrip(Trip trip, Passenger passenger, TravelCompany travelCompany) {
        em.getTransaction().begin();
        TripToPassengerToTravelCompanyRel relation = new TripToPassengerToTravelCompanyRel(passenger, trip, travelCompany);
        em.persist(relation);
        em.getTransaction().commit();
    }

    @Override
    public void cancelTrip(int passengerId, int tripNumber, int travelCompanyId) {
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT t FROM Trip t WHERE tripNumber= " + tripNumber);
        Trip trip = (Trip) query.getSingleResult();

        Query query1 = em.createQuery("SELECT t FROM TripToPassengerToTravelCompanyRel t WHERE trip= " +
                trip.getId() + " and passenger=" + passengerId + " and travelCompany=" + travelCompanyId);

        TripToPassengerToTravelCompanyRel relation= (TripToPassengerToTravelCompanyRel) query1.getSingleResult();
        em.remove(em.contains(relation) ? relation : em.merge(relation));
        em.getTransaction().commit();
    }
}
