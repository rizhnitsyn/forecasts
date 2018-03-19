package by.forecasts.repositories;

import by.forecasts.entities.PlayoffGroup;
import by.forecasts.entities.RegularGroup;
import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.TournamentState;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GroupTest extends BaseTest {

    @Test
    public void getListOfGroupsOfTournamentTest() {
        Team team1 = new Team("France");
        teamRepository.save(team1);

        TournamentState state = new TournamentState("active");
        tournamentStateRepository.save(state);
        Tournament tournament1 = new Tournament("Tournament 1", team1, LocalDate.now(), state);
        tournamentRepository.save(tournament1);

        RegularGroup regularGroup = new RegularGroup(4, 2, 2, "B", tournament1);
        PlayoffGroup playoffGroup = new PlayoffGroup(4, "A", tournament1,  true);

        regularGroup.addTeam(team1);
        regularGroupRepository.save(regularGroup);
        playoffGroupRepository.save(playoffGroup);
        List<RegularGroup> regularGroups = regularGroupRepository.findAllByTournamentId(tournament1.getId());
        List<PlayoffGroup> playoffGroups = playoffGroupRepository.findAllByTournamentId(tournament1.getId());
        regularGroup.delTeam(team1);

        assertEquals(regularGroup.getGroupName(), "B");
        assertThat(regularGroups, hasSize(1));
        assertThat(playoffGroups, hasSize(1));
    }
}
