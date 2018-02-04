package by.forecasts;

import by.forecasts.dao.TournamentDao;
import by.forecasts.entities.Tournament;

public class TournamentService {

    private static TournamentService INSTANCE;

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
        return TournamentDao.getInstance().getTournamentById(id);
    }
}
