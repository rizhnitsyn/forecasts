package by.forecasts.service.implementation;

import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.entities.Group;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.TournamentState;
import by.forecasts.entities.User;
import by.forecasts.repositories.GroupRepository;
import by.forecasts.repositories.TournamentRepository;
import by.forecasts.repositories.TournamentStateRepository;
import by.forecasts.repositories.UserRepository;
import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentStateRepository tournamentStateRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository, TournamentStateRepository tournamentStateRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentStateRepository = tournamentStateRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
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
    public Tournament findById(Long id) {
        return tournamentRepository.findOne(id);
    }

    @Override
    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    @Override
    public Tournament save(Tournament tournament) {
        TournamentState state = tournamentStateRepository.findOne(1L);
        tournament.setTournamentState(state);
        return tournamentRepository.save(tournament);
    }

    @Override
    public List<Tournament> getTournamentsFilterByUser(Long userId) {
        return tournamentRepository.getAllByUsersId(userId);
    }

    @Override
    public List<TournamentShortViewDto> getActiveTournamentsFilterByUser(Long userId) {
        List<Tournament> tournaments = tournamentRepository.getAllByUsersIdAndTournamentStateId(userId, 1L);
        return tournaments.stream()
                .map(tr -> new TournamentShortViewDto(tr.getId(), tr.getName(), tr.getStartDate()))
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
    public List<TournamentShortViewDto> getTournamentsByTeamParticipant(Long teamId) {
        List<Group> groups = groupRepository.findAllByTeamsInGroupId(teamId);
        return groups.stream()
                .map(Group::getTournament)
                .distinct()
                .map(tr -> new TournamentShortViewDto(tr.getId(), tr.getName(), tr.getStartDate()))
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
