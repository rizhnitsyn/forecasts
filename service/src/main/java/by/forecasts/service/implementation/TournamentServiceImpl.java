package by.forecasts.service.implementation;

import by.forecasts.dao.TournamentDao;
import by.forecasts.entities.Tournament;
import by.forecasts.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TournamentServiceImpl implements TournamentService {

    private final TournamentDao tournamentDao;

    @Autowired
    public TournamentServiceImpl(TournamentDao tournamentDao) {
        this.tournamentDao = tournamentDao;
    }

    @Override
    public Tournament getTournamentById(Long id) {
        return tournamentDao.findById(id);
    }

    @Override
    public List<Tournament> getListOfTournaments() {
        return tournamentDao.findAll();
    }

    @Override
    public List<Tournament> getTournamentsFilterByUser(Long userId) {
        return tournamentDao.getTournamentsFilterByUser(userId);
    }
}
