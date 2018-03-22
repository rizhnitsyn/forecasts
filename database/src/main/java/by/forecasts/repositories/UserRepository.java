package by.forecasts.repositories;

import by.forecasts.entities.User;
import by.forecasts.entities.UserState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    Page<User> findAllByOrderByIdDesc(Pageable pageable);

    List<User> findAllByTournamentsId(Long tournamentId);

    @Modifying
    @Query("update User u set u.userState = ?1 where u.id = ?2")
    void updateUserState(UserState userState, Long userId);
}
