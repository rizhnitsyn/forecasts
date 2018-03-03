CREATE TABLE myforecasts.tournaments
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tournament_name varchar(30) NOT NULL,
    team_organizer_id int(11) NOT NULL,
    tournament_start_date date NOT NULL,
    tournament_state_id int(11) NOT NULL,
    CONSTRAINT TOURNAMENTS_fk0 FOREIGN KEY (team_organizer_id) REFERENCES teams (id),
    CONSTRAINT TOURNAMENTS_fk1 FOREIGN KEY (tournament_state_id) REFERENCES tournament_states (id)
);
CREATE INDEX TOURNAMENTS_fk0 ON myforecasts.tournaments (team_organizer_id);
CREATE INDEX TOURNAMENTS_fk1 ON myforecasts.tournaments (tournament_state_id);
CREATE UNIQUE INDEX tournament_name ON myforecasts.tournaments (tournament_name);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('Чемпионат Европы 2016', 1, '2016-06-10', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('updated964000', 2, '2018-06-10', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('808373', 2, '2018-06-10', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('857167', 2, '2018-06-10', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('529727', 2, '2018-06-10', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('964626', 2, '2018-06-10', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('Тестовый турнир12', 39, '2020-08-12', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('Турнирчик', 31, '2019-12-12', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('Тестовый турнир13', 32, '2018-10-10', 1);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('New tournament 15', 38, '2020-08-12', 1);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('New Tournament 16', 17, '2020-10-10', 1);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('тур12', 22, '2020-08-12', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('729468', 2, '2017-11-29', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('401983', 2, '2017-11-30', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('Турнир в Японии', 26, '2018-12-10', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('Турнир в Японии 2', 21, '2018-12-10', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('Турнир в Японии 3', 21, '2018-12-12', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('Турнир в Японии 4', 21, '2018-12-10', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('Турнир в Японии 5', 21, '2018-12-12', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('Турнир в Японии 7', 21, '2018-12-12', 2);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('Новый турнир 20', 20, '2017-12-10', 1);
INSERT INTO myforecasts.tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) VALUES ('Турнир Минск', 38, '2017-12-22', 2);