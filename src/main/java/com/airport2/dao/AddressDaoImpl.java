package com.airport2.dao;

import com.airport2.EMF;
import com.airport2.entity.Address;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AddressDaoImpl implements AddressDao {

    EntityManager em = EMF.getEntityManager();

    @Override
    public Address getById(int id) {
        em.getTransaction().begin();
        Address address = em.find(Address.class, id);
        em.getTransaction().commit();
        return address;
    }

    @Override
    public void saveAddress(Address address){
        em.getTransaction().begin();
        em.persist(address);
        em.getTransaction().commit();
    }

    @Override
    public List<Address> getAllAddresses(){
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT a from Address a");
        List<Address> list = query.getResultList();
        em.getTransaction().commit();
        return list;
    }

    @Override
    public Set<Address> get(int page, int perPage, String sort) {
        em.getTransaction().begin();
        Query query = em.createQuery("select a from Address a order by " + sort);
        query.setFirstResult((page - 1) * perPage);
        query.setMaxResults(perPage);
        List<Address> list = query.getResultList();
        Set<Address> set = new LinkedHashSet<>(list);
        em.getTransaction().commit();
        return set;
    }

    @Override
    public Address update(Address address) {
        int addressId = address.getId();
        em.getTransaction().begin();

//made an experiment and executed update with session
        Session session = (Session) em.getDelegate();
        session.update(address);
        address = session.find(Address.class, addressId);
        em.getTransaction().commit();
        return address;
    }

    @Override
    public void delete(int addressId) {
        em.getTransaction().begin();
        Address address = getById(addressId);
        em.remove(em.contains(address) ? address : em.merge(address));
        em.getTransaction().commit();
    }
}
