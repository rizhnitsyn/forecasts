package by.forecasts.service.service;

import by.forecasts.entities.User;
import by.forecasts.entities.UserState;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;

import java.util.List;

public class UserServiceTest extends BaseServiceTest {

    @Test
    public void findOneTest() {
        User user = userService.findOne(1L);
        Assert.assertEquals(user.getFirstName(), "Андрей");
    }

    @Test
    public void findAllTest() {
        List<User> users = userService.findAll();
        User user = userService.findOne(1L);

        Assert.assertThat(users.size(), Matchers.greaterThan(20));
        Assert.assertThat(users, Matchers.hasItem(user));
    }

    @Test
    public void findAllUserStatesTest() {
        List<UserState> states = userService.findAllUserStates();
        Assert.assertThat(states, Matchers.hasSize(4));
    }

    @Test
    public void findAllPageOrderedTest() {
        Page<User> page = userService.findAllPageOrdered(0L);
        Assert.assertThat(page.getTotalPages(), Matchers.greaterThan(1));
        Assert.assertThat(page.getContent().size(),Matchers.equalTo(20));
    }
}
