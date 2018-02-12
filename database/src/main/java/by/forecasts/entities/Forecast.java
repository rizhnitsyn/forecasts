package by.forecasts.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "forecasts")
@ToString(callSuper = true)
public class Forecast extends BaseEntity {

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
