CREATE TABLE myforecasts.matches
(
    match_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_team_result int(11),
    second_team_result int(11),
    match_datetime datetime NOT NULL,
    match_state_id int(11) NOT NULL,
    first_team_id int(11) NOT NULL,
    second_team_id int(11) NOT NULL,
    tournament_id int(11) NOT NULL,
    CONSTRAINT PLANNED_MATCHES_fk0 FOREIGN KEY (match_state_id) REFERENCES match_states (match_state_id),
    CONSTRAINT PLANNED_MATCHES_fk2 FOREIGN KEY (first_team_id) REFERENCES teams (team_id),
    CONSTRAINT PLANNED_MATCHES_fk3 FOREIGN KEY (second_team_id) REFERENCES teams (team_id),
    CONSTRAINT PLANNED_MATCHES_fk4 FOREIGN KEY (tournament_id) REFERENCES tournaments (tournament_id)
);
CREATE INDEX PLANNED_MATCHES_fk0 ON myforecasts.matches (match_state_id);
CREATE INDEX PLANNED_MATCHES_fk2 ON myforecasts.matches (first_team_id);
CREATE INDEX PLANNED_MATCHES_fk3 ON myforecasts.matches (second_team_id);
CREATE INDEX PLANNED_MATCHES_fk4 ON myforecasts.matches (tournament_id);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (2, 1, '2016-06-10 17:00:00', 2, 1, 2, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 1, '2016-06-10 17:00:00', 2, 3, 4, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (2, 1, '2016-06-11 17:00:00', 2, 5, 6, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (1, 1, '2016-06-11 19:00:00', 2, 7, 8, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 1, '2016-06-12 17:00:00', 2, 9, 10, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (1, 0, '2016-06-12 19:00:00', 2, 11, 12, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (2, 0, '2016-06-12 21:00:00', 2, 13, 14, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (1, 0, '2016-06-13 17:00:00', 2, 15, 16, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (1, 1, '2016-06-13 19:00:00', 2, 17, 18, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 2, '2016-06-13 21:00:00', 2, 19, 20, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 2, '2016-06-14 17:00:00', 2, 21, 22, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (1, 1, '2016-06-14 19:00:00', 2, 23, 24, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (1, 2, '2016-06-15 17:00:00', 2, 8, 6, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (1, 1, '2016-06-15 19:00:00', 2, 2, 4, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (2, 0, '2016-06-15 21:00:00', 2, 1, 3, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (2, 1, '2016-06-16 17:00:00', 2, 7, 5, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 2, '2016-06-16 19:00:00', 2, 14, 12, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 0, '2016-06-16 21:00:00', 2, 13, 11, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (1, 0, '2016-06-17 17:00:00', 2, 20, 18, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (2, 2, '2016-06-17 19:00:00', 2, 16, 10, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (3, 0, '2016-06-17 21:00:00', 2, 15, 9, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (3, 0, '2016-06-18 17:00:00', 2, 19, 17, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (1, 1, '2016-06-18 19:00:00', 2, 24, 22, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 0, '2016-06-18 21:00:00', 2, 23, 21, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 1, '2016-06-19 17:00:00', 2, 2, 3, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 0, '2016-06-19 19:00:00', 2, 4, 1, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 3, '2016-06-20 17:00:00', 2, 8, 5, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 0, '2016-06-20 19:00:00', 2, 6, 7, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 1, '2016-06-21 17:00:00', 2, 14, 11, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 1, '2016-06-21 19:00:00', 2, 12, 13, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 2, '2016-06-21 21:00:00', 2, 16, 9, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (2, 1, '2016-06-21 23:00:00', 2, 10, 15, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (2, 1, '2016-06-22 17:00:00', 2, 24, 21, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (3, 3, '2016-06-22 19:00:00', 2, 22, 23, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 1, '2016-06-22 21:00:00', 2, 20, 17, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (0, 1, '2016-06-22 23:00:00', 2, 18, 19, 1);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (243439, 1, '2018-12-12 00:00:00', 1, 3, 4, 2);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (243439, 1, '2018-12-12 00:00:00', 1, 3, 4, 2);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (243439, 1, '2018-12-12 00:00:00', 1, 3, 4, 2);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (1, 1, '2018-12-12 00:00:00', 1, 4, 5, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (2, 0, '2018-12-12 00:00:00', 1, 4, 5, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (2, 3, '2018-12-12 00:00:00', 1, 4, 5, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (1, 0, '2018-12-12 00:00:00', 1, 4, 5, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (null, null, '2018-12-12 00:00:00', 1, 10, 12, 10);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (2, 2, '2018-12-12 00:00:00', 1, 4, 5, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (1, 0, '2018-12-12 00:00:00', 1, 4, 5, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (2, 7, '2018-12-12 00:00:00', 1, 4, 5, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (243439, 1, '2018-12-12 00:00:00', 1, 3, 4, 2);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (null, null, '2017-12-23 17:00:00', 1, 22, 15, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (null, null, '2017-12-24 18:30:00', 1, 13, 17, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (null, null, '2017-12-23 19:30:00', 1, 26, 24, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (null, null, '2017-12-30 18:30:00', 1, 25, 32, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (null, null, '2017-12-28 15:30:00', 1, 39, 29, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (null, null, '2017-12-05 15:30:00', 1, 33, 22, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (null, null, '2017-12-23 19:00:00', 1, 33, 28, 9);
INSERT INTO myforecasts.matches (first_team_result, second_team_result, match_datetime, match_state_id, first_team_id, second_team_id, tournament_id) VALUES (null, null, '2017-12-28 19:00:00', 1, 19, 26, 9);