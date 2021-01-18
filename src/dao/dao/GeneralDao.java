package dao.dao;

import java.util.List;

public interface GeneralDao<T> {



    T save(T t);

    T update(T t);

    boolean delete(Integer id);

    T find(Integer id);

    List<T> findAll();

    T findByName(String nom);


}
