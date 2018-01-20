package by.forecasts;

import org.junit.Assert;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void getUserNameTest() {
        Assert.assertEquals("Demo User", new UserDao().getUserName());
    }
}
