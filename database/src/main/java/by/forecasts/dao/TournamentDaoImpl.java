package by.forecasts.dao;

import by.forecasts.dao.common.BaseDaoImpl;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public final class TournamentDaoImpl extends BaseDaoImpl<Tournament> implements TournamentDao {

    @Override
    public List<Tournament> getTournamentsFilterByUser(Long userId) {
        List<User> result = getSessionFactory().getCurrentSession()
                .createQuery("select u from User u where u.id = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();

        User user = !result.isEmpty() ? result.get(0) : null;
        return user == null ? null : new ArrayList<>(user.getTournaments());
    }
}
