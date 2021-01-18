package dao.daoImpl;

import dao.dao.DBConnection;
import dao.dao.OperationDAO;
import model.Operation;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class OperationDAOImpl extends GeneralDaoImpl<Operation> implements OperationDAO {

    public OperationDAOImpl(){
        super("Operation");
    }

    @Override
    public List<Operation> findLDDate(Date maxDate) {
        System.out.println(String.format("Loading Precommandes with date less than : " + maxDate));
        Query q = DBConnection.getConnection().createQuery("SELECT p FROM Operation p WHERE p.dateOperation<=:maxDate").setParameter("maxDate", maxDate);
        return q.getResultList();
    }
}
