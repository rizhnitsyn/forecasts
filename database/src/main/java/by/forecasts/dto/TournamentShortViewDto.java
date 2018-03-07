package by.forecasts.dto;


import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.TournamentState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TournamentShortViewDto {

    private Long id;
    private String name;
    private LocalDate startDate;
    private String startDateString;
    private Team team;
    private TournamentState state;
    private Boolean registered;

    public TournamentShortViewDto(Long id, String name, LocalDate startDate, Team team, TournamentState state, Boolean registered) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.team = team;
        this.state = state;
        this.registered = registered;
    }

    public TournamentShortViewDto(Tournament tournament, Boolean registered) {
        this.id = tournament.getId();
        this.name = tournament.getName();
        this.startDate = tournament.getStartDate();
        this.team = tournament.getOrganizer();
        this.state = tournament.getTournamentState();
        this.registered = registered;
    }
}
