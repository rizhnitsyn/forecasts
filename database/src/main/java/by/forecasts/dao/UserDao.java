package by.forecasts.dao;

import by.forecasts.entities.Tournament;
import by.forecasts.entities.User;

public interface UserDao {

    void registerOnTournament(Tournament tournament, User user);
}
