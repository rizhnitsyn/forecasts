package by.forecasts;

public class UserService {

    public String getUserName() {
        return new UserDao().getUserName();
    }
}
