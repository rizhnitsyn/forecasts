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
@ToString
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_id")
    private Long id;

    @Column(name = "tournament_name", nullable = false, unique = true)
    private String name;

    @OneToMany
    @JoinColumn(name = "team_id")
    private Team organizer;

    @Column(name = "tournament_start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "tournament_state_id", nullable = false)
    private Long stateId;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Set<Match> matches = new HashSet<>();

    @ManyToMany
    @JoinColumn(name = "user_id")
    private Set<User> users = new HashSet<>();
}
