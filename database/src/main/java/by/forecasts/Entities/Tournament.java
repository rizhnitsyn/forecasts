package by.forecasts.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "tournaments")
@Entity
@ToString(exclude = {"matches", "groups", "users"})
public class Tournament {

    public Tournament(String name, Team organizer, LocalDate startDate, Long stateId) {
        this.name = name;
        this.organizer = organizer;
        this.startDate = startDate;
        this.stateId = stateId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_id")
    private Long id;

    @Column(name = "tournament_name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_organizer_id")
    private Team organizer;

    @Column(name = "tournament_start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "tournament_state_id", nullable = false)
    private Long stateId;

    @OneToMany(mappedBy = "tournament")
    private Set<Match> matches = new HashSet<>();

    @OneToMany(mappedBy = "tournament")
    private Set<TournamentGroups> groups = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "registration_desc",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();
}
