package dao.daoImpl;

import dao.dao.TaskDAO;
import model.Task;

public class TaskDaoImpl extends GeneralDaoImpl<Task> implements TaskDAO {
    public TaskDaoImpl() {
        super("Task");
    }
}
