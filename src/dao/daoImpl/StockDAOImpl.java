package dao.daoImpl;

import dao.dao.DBConnection;
import dao.dao.StockDAO;
import model.Stock;

import javax.persistence.Query;

public class StockDAOImpl extends GeneralDaoImpl<Stock> implements StockDAO {

    public StockDAOImpl() {
        super("Stock");
    }
}
