package by.forecasts.dao.implementation;

import by.forecasts.dao.UserDao;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;
import org.springframework.stereotype.Repository;


public final class UserDaoImpl implements UserDao {

    @Override
    public void registerOnTournament(Tournament tournament, User user) {
//        getSessionFactory().getCurrentSession().refresh(tournament);
//        tournament.getUsers().add(user);
//        user.getTournaments().add(tournament);
    }
}
