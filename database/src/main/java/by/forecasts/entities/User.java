package by.forecasts.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
@ToString(callSuper = true, exclude = "forecasts")
public class User extends BaseEntity {

    public User(String firstName, String secondName, String email, Long userState, String login, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.userState = userState;
        this.login = login;
        this.password = password;
    }

    @Column(name = "first_name", nullable = false)

    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_state_id", nullable = false)
    private Long userState;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(mappedBy = "users")
    private Set<Tournament> tournaments = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Forecast> forecasts = new HashSet<>();
}
