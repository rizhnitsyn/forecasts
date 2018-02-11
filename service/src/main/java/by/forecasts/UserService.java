package by.forecasts;

import by.forecasts.dao.UserDao;
import by.forecasts.entities.User;

import java.util.List;

public class UserService {

    private static UserService INSTANCE;
    private static final UserDao USER_DAO = UserDao.getInstance();

    private UserService() {}

    public static UserService getInstance() {
        if (INSTANCE == null) {
            synchronized (UserService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserService();
                }
            }
        }
        return INSTANCE;
    }

    public List<User> getListOfUsers() {
        return USER_DAO.findAll();
    }
}
