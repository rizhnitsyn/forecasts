package by.forecasts.repositories;

import by.forecasts.entities.UserState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStateRepository extends JpaRepository<UserState, Long> {
}
