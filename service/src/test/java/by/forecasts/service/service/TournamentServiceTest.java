package by.forecasts.service.service;

import by.forecasts.dto.TournamentShortViewDto;
import by.forecasts.entities.Tournament;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TournamentServiceTest extends BaseServiceTest {

    @Test
    public void findOneTest() {
        TournamentShortViewDto tournamentShortViewDto = tournamentService.findOne(1L, 1L);
        assertEquals(tournamentShortViewDto.getName(),  "Чемпионат Европы 2016");
    }

    @Test
    public void findAllTest() {
        List<Tournament> tournaments = tournamentService.findAll();
        assertThat(tournaments.size(), Matchers.greaterThan(3));
    }

    @Test
    public void getTournamentsFilterByUserTest() {
        List<Tournament> tournaments = tournamentService.getTournamentsFilterByUser(1L);
        assertThat(tournaments.size(), Matchers.greaterThan(2));
        assertThat(tournaments.stream()
                .map(Tournament::getName)
                .collect(Collectors.toList()),
                hasItem("Чемпионат Европы 2016"));
    }

    @Test
    public void getActiveTournamentsFilterByUserTest() {
        List<TournamentShortViewDto> tournaments = tournamentService.getActiveTournamentsFilterByUser(1L);
        assertThat(tournaments.stream()
                        .map(TournamentShortViewDto::getName)
                        .collect(Collectors.toList()),
                not(hasItem("Чемпионат Европы 2016")));
    }

    @Test
    public void getTournamentsByTeamParticipantTest() {
        List<TournamentShortViewDto> tournaments = tournamentService.getTournamentsByTeamParticipant(1L);
        assertThat(tournaments.stream()
                        .map(TournamentShortViewDto::getName)
                        .collect(Collectors.toList()),
                hasItem("Чемпионат Европы 2016"));
    }

    @Test
    public void closeTournamentTest() {
        tournamentService.closeTournament(9L);
        Tournament one = tournamentService.findById(9L);
        assertThat(one.getTournamentState().getId(), equalTo(2L));
    }
}
