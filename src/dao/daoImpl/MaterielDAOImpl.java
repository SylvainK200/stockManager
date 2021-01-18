package dao.daoImpl;


import dao.dao.MaterielDAO;
import model.Materiel;

public class MaterielDAOImpl extends GeneralDaoImpl<Materiel> implements MaterielDAO {

    public MaterielDAOImpl(){
        super("Materiel");
    }
}
