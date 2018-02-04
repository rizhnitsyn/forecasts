package by.forecasts;

import by.forecasts.dao.TournamentDao;
import by.forecasts.entities.Tournament;

public final class TournamentService {

    private static TournamentService instance;

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
        return TournamentDao.getInstance().getTournamentById(id);
    }
}
