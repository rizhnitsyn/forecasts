package by.forecasts;

import by.forecasts.dao.TournamentDaoImpl;
import by.forecasts.entities.Tournament;

import java.util.List;

public final class TournamentService {

    private static TournamentService instance;
    private static final TournamentDaoImpl TOURNAMENT_DAO = TournamentDaoImpl.getInstance();

    private TournamentService() {}

    public static TournamentService getInstance() {
        if (instance == null) {
            synchronized (TournamentService.class) {
                if (instance == null) {
                    instance = new TournamentService();
                }
            }
        }
        return instance;
    }

    public Tournament getTournamentById(Long id) {
        return TOURNAMENT_DAO.findById(id);
    }

    public List<Tournament> getListOfTournaments() {
        return TOURNAMENT_DAO.findAll();
    }
}
