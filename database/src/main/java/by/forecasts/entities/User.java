package by.forecasts.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
@ToString(callSuper = true, exclude = {"forecasts", "tournaments"})
public class User extends BaseEntity {

    public User(String firstName, String secondName, String email, UserState userState, String login, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.userState = userState;
        this.login = login;
        this.password = password;
    }

    @NotEmpty(message = "errors.field.empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "errors.field.empty")
    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Email(message = "errors.field.email")
    @NotEmpty(message = "errors.field.empty")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Version
    private LocalDateTime version;

    @ManyToOne
    @JoinColumn(name = "user_state_id", nullable = false)
    private UserState userState;

    @NotEmpty(message = "errors.field.empty")
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotEmpty(message = "errors.field.empty")
    @Size(min = 6, message = "errors.field.size")
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Tournament> tournaments = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Forecast> forecasts = new HashSet<>();

    @Transient
    public void addForecast(Forecast forecast) {
        forecasts.add(forecast);
    }
}
