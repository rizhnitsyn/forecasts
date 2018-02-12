package by.forecasts.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class SessionManager {

    private static final String MYSQL_CONFIG = "hibernate_mysql.cfg.xml";
    private static final String H2_CONFIG = "hibernate_h2.cfg.xml";
    private static SessionFactory sessionFactory = new Configuration().configure(MYSQL_CONFIG).buildSessionFactory();

    private SessionManager() {}

    private static void ConfigFactory(String configFile) {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
        sessionFactory = new Configuration().configure(configFile).buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setMySqlConfig() {
        ConfigFactory(MYSQL_CONFIG);
    }

    public static void setH2Config() {
        ConfigFactory(H2_CONFIG);
    }
}
