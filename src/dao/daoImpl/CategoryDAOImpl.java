package dao.daoImpl;

import dao.dao.CategoryDAO;
import dao.dao.OperationDAO;
import model.Category;
import model.Operation;

public class CategoryDAOImpl extends GeneralDaoImpl<Category> implements CategoryDAO {

    public CategoryDAOImpl(){
        super("Category");
    }
}
