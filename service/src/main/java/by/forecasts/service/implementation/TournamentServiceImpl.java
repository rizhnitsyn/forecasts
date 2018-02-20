package by.forecasts.service.implementation;

import by.forecasts.entities.Tournament;
import by.forecasts.repositories.TournamentRepository;
import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findOne(id);
    }

    @Override
    public List<Tournament> getListOfTournaments() {
        return null;
    }

    @Override
    public List<Tournament> getTournamentsFilterByUser(Long userId) {
        return null;
    }
}
