package utils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();

        } catch (Throwable throwable) {
            System.out.println("Session could not start");
            throw new ExceptionInInitializerError(throwable);
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
