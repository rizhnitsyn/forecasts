package by.forecasts.service.implementation;

import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import by.forecasts.repositories.TournamentRepository;
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

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
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
    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    @Override
    public List<Tournament> getTournamentsFilterByUser(Long userId) {
        return null;
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
}
