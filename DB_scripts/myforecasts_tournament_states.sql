CREATE TABLE myforecasts.tournament_states
(
    tournament_state_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tournament_state varchar(50) NOT NULL
);
CREATE UNIQUE INDEX tornament_state ON myforecasts.tournament_states (tournament_state);
INSERT INTO myforecasts.tournament_states (tournament_state) VALUES ('активен');
INSERT INTO myforecasts.tournament_states (tournament_state) VALUES ('окончен');