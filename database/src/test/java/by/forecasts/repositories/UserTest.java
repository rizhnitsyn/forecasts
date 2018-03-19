package by.forecasts.repositories;

import by.forecasts.entities.User;
import by.forecasts.entities.UserState;
import org.junit.Assert;
import org.junit.Test;

public class UserTest extends BaseTest {

    @Test
    public void userTest() {
        User user = new User();
        user.setFirstName("name1");
        user.setSecondName("name2");
        UserState userState = new UserState("11");
        userStateRepository.save(userState);
        user.setUserState(userState);
        user.setLogin("qwerty");
        user.setEmail("qwqw@by");
        user.setPassword("password");
        User save = userRepository.save(user);
        String sName = save.getSecondName();
        String password = save.getPassword();
        UserState userState1 = save.getUserState();

        Assert.assertEquals(save, user);
        Assert.assertEquals(save.getLogin(), user.getLogin());
    }

}
