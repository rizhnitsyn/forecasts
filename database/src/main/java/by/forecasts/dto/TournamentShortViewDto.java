package by.forecasts.dto;


import by.forecasts.entities.Team;
import by.forecasts.entities.Tournament;
import by.forecasts.entities.TournamentState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TournamentShortViewDto {

    private Long id;

    @NotEmpty(message = "errors.field.empty")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "errors.field.empty")
    private Team team;
    private TournamentState state;
    private Boolean registered;
    private Long matchesCount;

    public TournamentShortViewDto(Long id, String name, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
    }

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
