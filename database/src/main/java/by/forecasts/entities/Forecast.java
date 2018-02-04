package by.forecasts.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "forecasts")
@ToString
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forecast_id")
    private Long id;

    public Forecast(MatchScore matchForecast, User user, Match match) {
        this.matchForecast = matchForecast;
        this.user = user;
        this.match = match;
    }

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "firstResult", column = @Column(name = "first_team_forecast")),
            @AttributeOverride(name = "secondResult", column = @Column(name = "second_team_forecast"))
    })
    private MatchScore matchForecast;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
}
