CREATE TABLE myforecasts.match_states
(
    match_state_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    match_state varchar(30) NOT NULL
);
CREATE UNIQUE INDEX state_name ON myforecasts.match_states (match_state);
INSERT INTO myforecasts.match_states (match_state) VALUES ('Прием прогнозов окончен');
INSERT INTO myforecasts.match_states (match_state) VALUES ('Принимаются прогнозы на матч');