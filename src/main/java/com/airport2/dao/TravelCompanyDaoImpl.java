package com.airport2.dao;

import com.airport2.EMF;
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

public class TravelCompanyDaoImpl implements TravelCompanyDao{

    EntityManager em = EMF.getEntityManager();

    @Override
    public TravelCompany getById(int id) {
        em.getTransaction().begin();
        TravelCompany travelCompany = em.find(TravelCompany.class, id);
        em.getTransaction().commit();
        return travelCompany;
    }

    @Override
    public List<TravelCompany> getAllTravelCompanies() {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT a FROM TravelCompany a");
        List<TravelCompany> listOfTravelCompanies = query.getResultList();
        em.getTransaction().commit();
        return listOfTravelCompanies;
    }

    @Override
    public Set<TravelCompany> get(int page, int perPage, String sort) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT a FROM TravelCompany a ORDER BY " + sort);
        query.setFirstResult((page - 1) * perPage);
        query.setMaxResults(perPage);
        List<TravelCompany> list = query.getResultList();
        Set<TravelCompany> set = new LinkedHashSet<>();
        em.getTransaction().commit();
        return set;
    }

    @Override
    public void saveTravelCompany(TravelCompany travelCompany) {
        em.getTransaction().begin();
        em.persist(travelCompany);
        em.getTransaction().commit();

    }

    @Override
    public TravelCompany update(TravelCompany travelCompany) {
        int travelCompanyID = travelCompany.getId();
        em.getTransaction().begin();
        Session session = (Session) em.getDelegate();
        session.update(travelCompany);
        travelCompany = session.find(TravelCompany.class, travelCompanyID);
        em.getTransaction().commit();
        return travelCompany;
    }

    @Override
    public void delete(int travelCompanyId) {
        em.getTransaction().begin();
        TravelCompany travelCompany = getById(travelCompanyId);
        em.remove(em.contains(travelCompany) ? travelCompany : em.merge(travelCompany));
        em.getTransaction().commit();
    }

    @Override
    public List<TravelCompany> getTravelCompanyOfTrip(int tripNumber) {
        em.getTransaction().begin();
        Query query = em.createQuery("from Trip t where tripNumber=" + tripNumber);
        Trip trip = (Trip) query.getSingleResult();
        List<TravelCompany> travelCompanyList = new ArrayList<>();
        for (TripToPassengerToTravelCompanyRel t : trip.getTripToPassengerToTravelCompanyRels()) {
            travelCompanyList.add(t.getTravelCompany());
        }
        em.getTransaction().commit();
        return travelCompanyList;
    }
}
