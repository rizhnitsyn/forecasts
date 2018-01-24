package by.forecasts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;

public class UserDaoTest {
    private static final SessionFactory SESSION_FACTORY_MYSQL = new Configuration().configure("hibernate_mysql.cfg.xml").buildSessionFactory();
    private static final SessionFactory SESSION_FACTORY_H2 = new Configuration().configure("hibernate_h2.cfg.xml").buildSessionFactory();

    @Test
    public void getUserNameTest() {
        Assert.assertEquals("Demo User", new UserDao().getUserName());
    }

    @Test
    public void mysqlHibernateTest() {
        Session session = SESSION_FACTORY_MYSQL.openSession();

        Team team = session.find(Team.class, 22L);
        System.out.println(team);

        session.close();
        SESSION_FACTORY_MYSQL.close();
    }

    @Test
    public void h2HibernateTest() {
        Session session = SESSION_FACTORY_H2.openSession();

        Team team = session.find(Team.class, 1L);
        System.out.println(team);

        session.close();
        SESSION_FACTORY_H2.close();
    }
}
