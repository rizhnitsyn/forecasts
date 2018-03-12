package by.forecasts.service;

import by.forecasts.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();

    User findByLogin(String login);
}
