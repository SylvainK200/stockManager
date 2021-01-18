package dao.dao;

import model.Operation;
import model.Precommande;

import java.util.Date;
import java.util.List;

public interface OperationDAO extends GeneralDao<Operation>{
    List<Operation> findLDDate(Date maxDate);
}
