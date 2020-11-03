package dao;

import entities.Discipline;
import entities.User;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.SessionUtil;

import javax.persistence.GeneratedValue;
import java.sql.SQLException;
import java.util.List;

public class DisciplineDAO extends SessionUtil implements GenericDAO <Discipline> {

    @Override public void addToDatabase(Discipline discipline) throws SQLException {
        openTransactionSession();
        Session session=getSession();
        session.save(discipline);
        closeTransactionSession();
    }

    @Override public List<Discipline> getAllFromDatabase() throws SQLException {
        return null; }

    @Override public void update(Discipline discipline) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.update(discipline);
        closeTransactionSession();
    }

    @Override public void delete(Discipline discipline) throws SQLException {
        openTransactionSession();
        Session session=getSession();
        session.delete(discipline);
        closeTransactionSession();
    }

    public List<Discipline> listAllDisciplines(int numberOfUsers){
        openTransactionSession();
        Session session=getSession();
        Query query =session.createQuery("from Discipline d  where d.users.size <= :a");
        query.setParameter("a",numberOfUsers);
        List<Discipline> allDisciplines= query.list();
        return allDisciplines;

    }

}
