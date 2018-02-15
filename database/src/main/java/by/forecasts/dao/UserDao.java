package by.forecasts.dao;

import by.forecasts.dao.common.BaseDao;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;

public interface UserDao extends BaseDao<User> {

    void registerOnTournament(Tournament tournament, User user);
}
