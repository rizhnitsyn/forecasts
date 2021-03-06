package by.forecasts.service;

import by.forecasts.dto.UserWithResultsDto;
import by.forecasts.entities.User;
import by.forecasts.entities.UserState;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();

    User saveUser(User user);

    User findOne(Long id);

    List<UserState> findAllUserStates();

    void updateUser(User user);

    Page<User> findAllPageOrdered(Long pageId);

    List<UserWithResultsDto> getUsersWithStatistic(Long tournamentId);
}
