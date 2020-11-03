package utils;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionUtil {
    private Session session;
    private Transaction transaction;

    public Session getSession() { return session; }

    public Transaction getTransaction() { return transaction; }

    public Session openSession(){
        return HibernateUtils.getSessionFactory().openSession();
    }
    public Session openTransactionSession(){
        session=openSession();
        transaction=session.beginTransaction();
        return session;
    }
    private void closeSession(){
        session.close();
    }
    public void closeTransactionSession(){
        transaction.commit();
        closeSession();
    }
}

