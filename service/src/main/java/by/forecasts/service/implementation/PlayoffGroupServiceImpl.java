package by.forecasts.service.implementation;

import by.forecasts.entities.PlayoffGroup;
import by.forecasts.entities.Tournament;
import by.forecasts.repositories.PlayoffGroupRepository;
import by.forecasts.repositories.TournamentRepository;
import by.forecasts.service.PlayoffGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayoffGroupServiceImpl implements PlayoffGroupService {

    private final PlayoffGroupRepository playoffGroupRepository;
    private final TournamentRepository tournamentRepository;

    @Autowired
    public PlayoffGroupServiceImpl(PlayoffGroupRepository playoffGroupRepository, TournamentRepository tournamentRepository) {
        this.playoffGroupRepository = playoffGroupRepository;
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public void save(PlayoffGroup playoffGroup, Long tournamentId) {
        Tournament tournament = tournamentRepository.findOne(tournamentId);
        playoffGroup.setTournament(tournament);
        playoffGroupRepository.save(playoffGroup);
    }
}

