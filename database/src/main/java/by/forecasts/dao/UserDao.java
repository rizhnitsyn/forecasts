package by.forecasts.dao;

import by.forecasts.entities.User;

public class UserDao extends BaseDao<User> {

    private static UserDao INSTANCE;

    private UserDao() {
        super(User.class);
    }

    public static UserDao getInstance() {
        if (INSTANCE == null) {
            synchronized (MatchDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDao();
                }
            }
        }
        return INSTANCE;
    }
}
