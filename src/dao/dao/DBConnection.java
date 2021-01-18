package dao.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DBConnection {
    private static EntityManager em ;
    static{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("trip");
        em = emf.createEntityManager();
    }
    public static EntityManager getConnection(){
        return em ;
    }
    public static EntityTransaction getEntityTransaction(){
        return em.getTransaction();
    }
}
