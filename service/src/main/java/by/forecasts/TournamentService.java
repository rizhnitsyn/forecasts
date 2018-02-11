package by.forecasts;

import by.forecasts.dao.TournamentDao;
import by.forecasts.entities.Tournament;

import java.util.List;

public final class TournamentService {

    private static TournamentService INSTANCE;
    private static final TournamentDao TOURNAMENT_DAO = TournamentDao.getInstance();

    private TournamentService() {}

    public static TournamentService getInstance() {
        if (INSTANCE == null) {
            synchronized (TournamentService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TournamentService();
                }
            }
        }
        return INSTANCE;
    }

    public Tournament getTournamentById(Long id) {
        return TOURNAMENT_DAO.findById(id);
    }

    public List<Tournament> getListOfTournaments() {
        return TOURNAMENT_DAO.findAll();
    }
}
