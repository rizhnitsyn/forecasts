package by.forecasts.service.implementation;

import by.forecasts.dto.PlayoffGroupDto;
import by.forecasts.entities.Match;
import by.forecasts.entities.PlayoffGroup;
import by.forecasts.entities.Tournament;
import by.forecasts.repositories.MatchRepository;
import by.forecasts.repositories.PlayoffGroupRepository;
import by.forecasts.repositories.TournamentRepository;
import by.forecasts.service.PlayoffGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlayoffGroupServiceImpl implements PlayoffGroupService {

    private final PlayoffGroupRepository playoffGroupRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public PlayoffGroupServiceImpl(PlayoffGroupRepository playoffGroupRepository, TournamentRepository tournamentRepository, MatchRepository matchRepository) {
        this.playoffGroupRepository = playoffGroupRepository;
        this.tournamentRepository = tournamentRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public void save(PlayoffGroup playoffGroup, Long tournamentId) {
        Tournament tournament = tournamentRepository.findOne(tournamentId);
        playoffGroup.setTournament(tournament);
        playoffGroupRepository.save(playoffGroup);
    }

    @Override
    public List<PlayoffGroupDto> findAllByTournamentId(Long tournamentId) {
        List<PlayoffGroup> groups = playoffGroupRepository.findAllByTournamentId(tournamentId);

        return groups.stream()
                .map(PlayoffGroupDto::new)
                .map(this::setMatches)
                .sorted(Comparator.comparing(PlayoffGroupDto::getId).reversed())
                .collect(Collectors.toList());
    }

    private PlayoffGroupDto setMatches (PlayoffGroupDto dto) {
        List<Match> matches = matchRepository.findAllByGroupId(dto.getId());
        dto.setMatches(matches);
        return dto;
    }
}

