package dao.dao;

import model.Precommande;

import java.util.Date;
import java.util.List;

public interface PrecommandeDAO extends GeneralDao<Precommande>{

    List<Precommande> findLDDate(Date maxDate);
    public List<Precommande> filterPrecommandeByPrix(String nom);
    Precommande findBN(String nom);
}
