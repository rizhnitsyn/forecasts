package by.forecasts.services;

import by.forecasts.dao.TournamentDao;
import by.forecasts.entities.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TournamentService {

    private final TournamentDao tournamentDao;

    @Autowired
    public TournamentService(TournamentDao tournamentDao) {
        this.tournamentDao = tournamentDao;
    }


    public Tournament getTournamentById(Long id) {
        return tournamentDao.findById(id);
    }

    public List<Tournament> getListOfTournaments() {
        return tournamentDao.findAll();
    }
}
