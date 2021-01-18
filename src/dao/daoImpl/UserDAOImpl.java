package dao.daoImpl;

import dao.dao.GeneralDao;
import dao.dao.StockDAO;
import dao.dao.UserDAO;
import model.User;

public class UserDAOImpl extends GeneralDaoImpl<User> implements UserDAO {

    public UserDAOImpl() {
        super("User");
    }
}
