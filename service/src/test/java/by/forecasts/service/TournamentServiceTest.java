package by.forecasts.service;

import by.forecasts.entities.Tournament;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TournamentServiceTest extends BaseServiceTest {

    @Test
    public void getTournamentByIdTest() {
        Tournament tournamentById = tournamentService.findOne(1L);
        assertEquals(tournamentById.getName(),  "Чемпионат Европы 2016");
    }

    @Test
    public void getListOfTournamentsTest() {
        List<Tournament> tournaments = tournamentService.findAll();
        assertThat(tournaments.size(), Matchers.greaterThan(3));
    }

    @Test
    public void getTournamentsFilterByUserTest() {
        List<Tournament> tournaments = tournamentService.getTournamentsFilterByUser(1L);
        assertThat(tournaments.size(), Matchers.greaterThan(3));
    }
}
