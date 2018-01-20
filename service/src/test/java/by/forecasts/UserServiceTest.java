package by.forecasts;

import org.junit.Assert;
import org.junit.Test;

public class UserServiceTest {

    @Test
    public void getUserNameTest() {
        Assert.assertEquals("Demo User", new UserService().getUserName());
    }
}
