package dao.daoImpl;

import dao.dao.DBConnection;
import dao.dao.PrecommandeDAO;
import model.Precommande;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class PrecommandeDAOImpl extends GeneralDaoImpl<Precommande> implements PrecommandeDAO {

    public PrecommandeDAOImpl() {
        super("Precommande");
    }


    @Override
    public List<Precommande> findLDDate(Date maxDate) {
        System.out.println(String.format("Loading Precommandes with date less than : " + maxDate));
        Query q = DBConnection.getConnection().createQuery("SELECT p FROM Precommande p WHERE p.dateDisponibilite<=:maxDate").setParameter("maxDate", maxDate);
        return q.getResultList();
    }

    @Override
    public Precommande findBN(String nom) {
        Query q = DBConnection.getConnection().createQuery("SELECT p FROM Precommande p JOIN p.materiel m WHERE m.nom=:nom").setParameter("nom", nom);
        return (Precommande) q.getSingleResult();
    }

    public List<Precommande> filterPrecommandeByPrix(String nom){
        Query q = DBConnection.getConnection().createQuery("Select p from Precommande p JOIN p.materiel m where m.nom=:nom order by p.prixUnitaire");
        return q.getResultList();
    }
}
