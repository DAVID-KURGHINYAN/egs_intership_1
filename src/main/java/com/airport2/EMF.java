package com.airport2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EMF {

    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;
    final private static String PU = "airport";

    public static EntityManager getEntityManager() {
        if (em == null || !em.isOpen()){
            init();
        }
        return em;
    }

    public static void closeConnection() {
        em.close();
        emf.close();
    }

    private static void init(){
        try {
            emf = Persistence.createEntityManagerFactory(PU);
            em = emf.createEntityManager();
        } catch(Exception e) {
            System.err.println("unable to open DataBase Connection");
        }
    }
}

