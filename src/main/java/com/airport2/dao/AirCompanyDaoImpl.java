package com.airport2.dao;

import com.airport2.EMF;
import com.airport2.entity.Address;
import com.airport2.entity.AirCompany;
import com.airport2.entity.ZipCode;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AirCompanyDaoImpl implements AirCompanyDao {

    EntityManager em = EMF.getEntityManager();
    @Override
    public AirCompany getById(int id) {
        em.getTransaction().begin();
        AirCompany airCompany = em.find(AirCompany.class, id);
        em.getTransaction().commit();
        return airCompany;
    }

    @Override
    public List<AirCompany> getAllAirCompanies() {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT a FROM AirCompany a");
        List<AirCompany> list = query.getResultList();
        em.getTransaction().commit();
        return list;
    }

    @Override
    public Set<AirCompany> get(int page, int perPage, String sort) {
        em.getTransaction().begin();
        Query query = em.createQuery("select a from Passenger a order by " + sort);
        query.setFirstResult((page - 1) * perPage);
        query.setMaxResults(perPage);
        List<AirCompany> list = query.getResultList();
        Set<AirCompany> set = new LinkedHashSet<>(list);
        em.getTransaction().commit();
        return set;
    }

    @Override
    public void saveAirCompany(AirCompany airCompany) {
        em.getTransaction().begin();
        em.persist(airCompany);
        em.getTransaction().commit();
    }

    @Override
    public AirCompany update(AirCompany airCompany) {
        int airCompanyId = airCompany.getId();
        em.getTransaction().begin();
        Session session = (Session) em.getDelegate();
        session.update(airCompany);
        airCompany = session.find(AirCompany.class, airCompanyId);
        em.getTransaction().commit();
        return airCompany;
    }

    @Override
    public void delete(int airCompanyId) {
        AirCompany airCompany = getById(airCompanyId);
        em.getTransaction().begin();
        em.remove(em.contains(airCompany) ? airCompany : em.merge(airCompany));
        em.getTransaction().commit();
    }

    public AirCompany createAirCompany() {
        ZipCode zipCode = new ZipCode();
        String postalCode = "00";
        for (int i = 1; i < 100; i++) {
            postalCode+=i;

            //Create Zip Code for new address.
            zipCode.setPostalCode(postalCode);
            zipCode.setCountry("Armenia");
            zipCode.setCity("Yerevan");
            postalCode="00";
        }

//Create address for new air company.
        Address address = new Address();
        address.setStreet("Amiryan");
        address.setZipCode(zipCode);

//Create air company for save in DATABASE.
        AirCompany ac = new AirCompany();
        ac.setName("Arm-Avia");
        ac.setAddress(address);
        ac.setFoundDate(LocalDate.parse("1998-05-25"));
        return ac;
    }
}
