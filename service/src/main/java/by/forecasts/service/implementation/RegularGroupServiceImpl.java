package by.forecasts.service.implementation;

import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.Tournament;
import by.forecasts.repositories.RegularGroupRepository;
import by.forecasts.repositories.TournamentRepository;
import by.forecasts.service.RegularGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegularGroupServiceImpl implements RegularGroupService {

    private final RegularGroupRepository regularGroupRepository;
    private final TournamentRepository tournamentRepository;

    @Autowired
    public RegularGroupServiceImpl(RegularGroupRepository regularGroupRepository, TournamentRepository tournamentRepository) {
        this.regularGroupRepository = regularGroupRepository;
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public void save(RegularGroup regularGroup, Long tournamentId) {
        Tournament tournament = tournamentRepository.findOne(tournamentId);
        regularGroup.setTournament(tournament);
        regularGroupRepository.save(regularGroup);
    }

    @Override
    public List<RegularGroup> findAllByTournamentId(Long tournamentId) {
        return regularGroupRepository.findAllByTournamentId(tournamentId);
    }
}
