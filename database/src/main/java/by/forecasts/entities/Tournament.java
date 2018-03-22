package by.forecasts.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "tournaments")
@Entity
@ToString(callSuper = true, exclude = {"matches", "groups", "users"})
public class Tournament extends BaseEntity {

    public Tournament(String name, Team organizer, LocalDate startDate, TournamentState tournamentState) {
        this.name = name;
        this.organizer = organizer;
        this.startDate = startDate;
        this.tournamentState = tournamentState;
    }

    @NotEmpty(message = "errors.field.empty")
    @Column(name = "tournament_name", nullable = false, unique = true)
    private String name;

    @NotNull(message = "errors.field.empty")
    @ManyToOne
    @JoinColumn(name = "team_organizer_id", nullable = false)
    private Team organizer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tournament_start_date", nullable = false)
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "tournament_state_id", nullable = false)
    private TournamentState tournamentState;

    @OneToMany(mappedBy = "tournament")
    private Set<Match> matches = new HashSet<>();

    @OneToMany(mappedBy = "tournament")
    private Set<Group> groups = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "registration_desc",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    @Transient
    public void registerOnTournament(User user) {
        this.users.add(user);
    }
}
