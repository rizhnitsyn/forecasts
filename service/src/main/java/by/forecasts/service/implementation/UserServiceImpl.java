package by.forecasts.service.implementation;

import by.forecasts.dao.UserDao;
import by.forecasts.entities.User;
import by.forecasts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getListOfUsers() {
        return userDao.findAll();
    }
}
