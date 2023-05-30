package dao.daoImpl;

import dao.dao.PersonDAO;
import model.Person;

public class PersonDaoImpl extends GeneralDaoImpl<Person> implements PersonDAO {
    public PersonDaoImpl() {
        super("Person");
    }
}
