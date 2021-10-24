package com.airport2.dao;

import com.airport2.EMF;
import com.airport2.entity.ZipCode;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public class ZipCodeDaoImpl implements ZipCodeDao {
    EntityManager em = EMF.getEntityManager();


    @Override
    public ZipCode getById(int id) {
        em.getTransaction().begin();
        ZipCode zipCode = em.find(ZipCode.class, id);
        em.getTransaction().commit();
        return zipCode;
    }

    @Override
    public void saveZipCode(ZipCode zipCode) {
        em.getTransaction().begin();
        em.persist(zipCode);
        em.getTransaction().commit();
    }

    @Override
    public List<ZipCode> getZipCodes() {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT a FROM ZipCode a");
        List<ZipCode> list = (List<ZipCode>) query.getResultList();
        em.getTransaction().commit();
        return list;
    }

    @Override
    public Set<ZipCode> get(int page, int perPage, String sort) {
        em.getTransaction().begin();
        int minimalRowIndex = page * perPage - perPage;
        Query query = em.createQuery("SELECT a FROM ZipCode a ORDER BY " + sort + " OFFSET " +
                minimalRowIndex + "ROW FETCH NEXT " + perPage + " ROWS ONLY");
        Set<ZipCode> set = (Set<ZipCode>) query.getResultList();
        em.getTransaction().commit();
        return set;
    }

    @Override
    public void update(ZipCode zipCode) {
        em.getTransaction().begin();
        Session session = (Session) em.getDelegate();
        session.update(zipCode);
        em.getTransaction().commit();
    }

    @Override
    public void delete(int zipCodeId) {
        ZipCode zipCode = getById(zipCodeId);
        em.getTransaction().begin();
        em.remove(zipCode);
        em.getTransaction().commit();
    }


}
