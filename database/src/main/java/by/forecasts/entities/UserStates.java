package by.forecasts.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "user_states")
public class UserStates extends BaseEntity {

    @Column(name = "user_state", nullable = false)
    private String userState;
}
