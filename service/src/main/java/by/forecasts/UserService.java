package by.forecasts;

import by.forecasts.dao.UserDao;
import by.forecasts.entities.User;

import java.util.List;

public final class UserService {

    private static UserService instance;
    private static final UserDao USER_DAO = UserDao.getInstance();

    private UserService() {}

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    public List<User> getListOfUsers() {
        return USER_DAO.findAll();
    }
}
