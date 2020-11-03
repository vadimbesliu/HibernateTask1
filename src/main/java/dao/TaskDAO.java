package dao;

import entities.Task;
import org.hibernate.Session;
import utils.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class TaskDAO extends SessionUtil implements GenericDAO <Task>{


    @Override
    public void addToDatabase(Task task) throws SQLException {
        openTransactionSession();
        Session session=getSession();
        session.save(task);
        closeTransactionSession();
    }

    @Override
    public List<Task> getAllFromDatabase() throws SQLException { return null; }

    @Override
    public void update(Task task) throws SQLException {
        openTransactionSession();
        Session session=getSession();
        session.save(task);
        closeTransactionSession();
    }

    @Override
    public void delete(Task task) throws SQLException {
        openTransactionSession();
        Session session=getSession();
        session.save(task);
        closeTransactionSession();
    }
}
