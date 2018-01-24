package by.forecasts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;

public class UserDaoTest {
    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    @Test
    public void getUserNameTest() {
        Assert.assertEquals("Demo User", new UserDao().getUserName());
    }

    @Test
    public void userHibernateTest() {
        Session session = SESSION_FACTORY.openSession();

        Team team = session.find(Team.class, 22L);
        System.out.println(team);

        session.close();
        SESSION_FACTORY.close();
    }
}
