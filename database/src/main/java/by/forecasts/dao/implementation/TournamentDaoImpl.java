package by.forecasts.dao.implementation;

import by.forecasts.dao.TournamentDao;
import by.forecasts.entities.Tournament;
import org.springframework.stereotype.Repository;

import java.util.List;


public final class TournamentDaoImpl implements TournamentDao {

    @Override
    public List<Tournament> getTournamentsFilterByUser(Long userId) {
        return null;
//        List<User> result = getSessionFactory().getCurrentSession()
//                .createQuery("select u from User u where u.id = :userId", User.class)
//                .setParameter("userId", userId)
//                .getResultList();
//
//        User user = !result.isEmpty() ? result.get(0) : null;
//        return user == null ? null : new ArrayList<>(user.getTournaments());
    }
}
