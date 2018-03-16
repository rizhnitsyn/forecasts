package by.forecasts.service.implementation;

import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.TournamentState;
import by.forecasts.entities.User;
import by.forecasts.repositories.TeamRepository;
import by.forecasts.repositories.TournamentRepository;
import by.forecasts.repositories.TournamentStateRepository;
import by.forecasts.repositories.UserRepository;
import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Transactional
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentStateRepository tournamentStateRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.UK);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.UK);

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository, TournamentStateRepository tournamentStateRepository, TeamRepository teamRepository, UserRepository userRepository) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentStateRepository = tournamentStateRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TournamentShortViewDto findOne(Long id, Long userId) {
        Tournament tournament = tournamentRepository.findOne(id);
        Boolean registered = tournament.getUsers().stream()
                .filter(user -> user.getId().equals(userId))
                .map(user -> true)
                .findFirst().orElse(false);
        return new TournamentShortViewDto(tournament, registered);
    }

    @Override
    public TournamentShortViewDto findOne(Long id) {
        Tournament tournament = tournamentRepository.findOne(id);
        TournamentShortViewDto tournamentShortViewDto = new TournamentShortViewDto();
        tournamentShortViewDto.setId(tournament.getId());
        tournamentShortViewDto.setName(tournament.getName());
        return tournamentShortViewDto;
    }

    @Override
    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    @Override
    public Tournament save(TournamentShortViewDto tournament) {
        TournamentState state = tournamentStateRepository.findOne(1L);
        Team team = teamRepository.findOne(tournament.getTeam().getId());
        LocalDate startDate = LocalDate.parse(tournament.getStartDateString(), DATE_FORMAT);
        Tournament newTournament = new Tournament(
                tournament.getName(),
                team,
                startDate,
                state);
        return tournamentRepository.save(newTournament);
    }

    @Override
    public List<Tournament> getTournamentsFilterByUser(Long userId) {
        return null;
    }

    @Override
    public List<TournamentShortViewDto> getActiveTournamentsFilterByUser(Long userId) {
        List<Tournament> tournaments = tournamentRepository.getAllByUsersIdAndTournamentStateId(userId, 1L);
        return tournaments.stream()
                .map(tr -> new TournamentShortViewDto(tr.getId(), tr.getName(), tr.getStartDate().format(DATE_TIME_FORMATTER)))
                .collect(Collectors.toList());
    }

    @Override
    public List<TournamentShortViewDto> getTournamentsFilterByState(Long stateId, Long userId) {
        List<Tournament> tournamentList = tournamentRepository.getAllByTournamentStateId(stateId);
        return tournamentList.stream()
                .map(tr -> new TournamentShortViewDto(tr.getId(), tr.getName(), tr.getStartDate(), tr.getOrganizer(), tr.getTournamentState(),
                        tr.getUsers().stream()
                        .filter(user -> user.getId().equals(userId))
                        .map(user -> true)
                        .findFirst().orElse(false)))
                .sorted(Comparator.comparing(TournamentShortViewDto::getStartDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void registerOnTournament(Long tournamentId, Long userId) {
        Tournament tournament = tournamentRepository.findOne(tournamentId);
        User user = userRepository.findOne(userId);
        tournament.registerOnTournament(user);
    }

    @Override
    public void closeTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findOne(tournamentId);
        TournamentState newState = tournamentStateRepository.getOne(2L);
        tournament.setTournamentState(newState);
    }
}
