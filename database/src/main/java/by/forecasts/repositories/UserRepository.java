package by.forecasts.repositories;

import by.forecasts.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    Page<User> findAllByOrderByIdDesc(Pageable pageable);
}
