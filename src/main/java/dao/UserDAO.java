package dao;
import entities.Discipline;
import entities.Role;
import entities.User;
import entities.enums.DisciplineTypes;
import entities.enums.StatusTypes;
import org.hibernate.Session;
import org.hibernate.Query;
import utils.SessionUtil;

import java.sql.SQLException;
import java.util.List;

public class UserDAO extends SessionUtil implements GenericDAO <User>{


    @Override
    public void addToDatabase(User user) throws SQLException {
        openTransactionSession();
        Session session=getSession();
        session.save(user);
        closeTransactionSession();
    }

    @Override
    public List<User> getAllFromDatabase() throws SQLException { return null; }

    @Override
    public void update(User user) throws SQLException {
        openTransactionSession();
        Session session=getSession();
        session.save(user);
        closeTransactionSession();
    }

    @Override
    public void delete(User user) throws SQLException {
        openTransactionSession();
        Session session=getSession();
        session.delete(user);
        closeTransactionSession();
    }
    public List<User> listAllUsersByRole(String role){
        openTransactionSession();
        Session session=getSession();
        Query query =session.createQuery("from User u join fetch u.roleSet r where r.name =:role");
        query.setParameter("role",role);
        List<User> allUserList= query.list();
        return allUserList;
    }
    public List<User> listAllUsersByDiscipline(DisciplineTypes discipline){
        openTransactionSession();
        Session session=getSession();
        Query query =session.createQuery("from User u join fetch u.discipline d where d.Name =:discipline");
        query.setParameter("discipline",discipline);
        List<User> allUserList= query.list();
        return allUserList;
    }
    public List<User> listAllUsersByTaskInTODO(StatusTypes statusType){
        openTransactionSession();
        Session session=getSession();
        Query query =session.createQuery("from User u join fetch u.taskList d where d.status =:status");
        query.setParameter("status",statusType);
        List<User> allUserList= query.list();
        return allUserList;
    }
}
