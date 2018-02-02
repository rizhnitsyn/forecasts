package by.forecasts.Entities;

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
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forecast_id")
    private Long id;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "firstResult", column = @Column(name = "first_team_forecast")),
            @AttributeOverride(name = "secondResult", column = @Column(name = "second_team_forecast"))
    })
    private MatchScore matchForecast;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "match_id")
    private Match match;
}
