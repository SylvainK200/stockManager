package dao.daoImpl;

import dao.dao.DBConnection;
import dao.dao.GeneralDao;

import javax.persistence.Query;
import java.util.List;

public class GeneralDaoImpl<T> implements GeneralDao<T> {

    public GeneralDaoImpl(String table_name){
        this.table_name = table_name;
    }


    private String table_name;

    @Override
    public T save(T t) {
        System.out.println(String.format("saving " + table_name + " : " + t));
        DBConnection.getEntityTransaction().begin();
        DBConnection.getConnection().persist(t);
        DBConnection.getEntityTransaction().commit();
        return t;
    }

    @Override
    public T update(T t) {
        System.out.println(String.format("updating " + table_name + " : " + t));
        DBConnection.getEntityTransaction().begin();
        T t1 = DBConnection.getConnection().merge(t);
        DBConnection.getEntityTransaction().commit();
        return t1;
    }

    @Override
    public boolean delete(Integer id) {
        System.out.println(String.format("Deleting " + table_name + " with id : " + id));
        Query q = DBConnection.getConnection().createQuery("DELETE FROM " + table_name + " t WHERE t.id=:id").setParameter("id", id);
        DBConnection.getEntityTransaction().begin();
        if (q.executeUpdate() > 0) {
            DBConnection.getEntityTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public T find(Integer id) {
        System.out.println(String.format("Loading " + table_name + " with id : " + id));
        Query q = DBConnection.getConnection().createQuery("SELECT t FROM " + table_name + " t WHERE t.id=:id").setParameter("id", id);
        return (T) q.getSingleResult();
    }

    @Override
    public List<T> findAll() {
        System.out.println(String.format("Loading all " + table_name));
        Query q = DBConnection.getConnection().createQuery("SELECT t FROM " + table_name + " t");
        return q.getResultList();
    }

    @Override
    public T findByName(String nom){
        System.out.println(String.format("Loading " + table_name + " with name : " + nom));
        Query q = DBConnection.getConnection().createQuery("SELECT t FROM " + table_name + " t WHERE t.nom=:nom").setParameter("nom", nom);

        return (T) q.getSingleResult();
    }
}
