package dao;

import entities.Role;
import org.hibernate.Session;
import utils.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class RoleDAO extends SessionUtil implements GenericDAO<Role> {

    @Override
    public void addToDatabase(Role role) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(role);
        closeTransactionSession();
    }

    @Override
    public List<Role> getAllFromDatabase() throws SQLException {
        return null;
    }

    @Override
    public void update(Role role) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(role);
        closeTransactionSession();
    }

    @Override
    public void delete(Role role) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(role);
        closeTransactionSession();
    }
}
