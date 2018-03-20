package by.forecasts.repositories;

import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    List<Tournament> getAllByUsersId(Long userId);

    List<Tournament> getAllByUsersIdAndTournamentStateId(Long userId, Long stateId);

    List<Tournament> getAllByTournamentStateId(Long tournamentStateId);
}
