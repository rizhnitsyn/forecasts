package by.forecasts.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    public Tournament(String name, Team organizer, LocalDate startDate, Long stateId) {
        this.name = name;
        this.organizer = organizer;
        this.startDate = startDate;
        this.stateId = stateId;
    }

    @Column(name = "tournament_name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_organizer_id", nullable = false)
    private Team organizer;

    @Column(name = "tournament_start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "tournament_state_id", nullable = false)
    private Long stateId;

    @OneToMany(mappedBy = "tournament", fetch = FetchType.EAGER)
    private Set<Match> matches = new HashSet<>();

    @OneToMany(mappedBy = "tournament")
    private Set<Group> groups = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "registration_desc",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();
}
