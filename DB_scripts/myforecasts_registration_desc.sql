CREATE TABLE myforecasts.registration_desc
(
    tournament_id int(11) NOT NULL,
    user_id int(11) NOT NULL,
    CONSTRAINT REGISTRATION_DESC_fk0 FOREIGN KEY (tournament_id) REFERENCES tournaments (id),
    CONSTRAINT REGISTRATION_DESC_fk1 FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE INDEX REGISTRATION_DESC_fk1 ON myforecasts.registration_desc (user_id);
CREATE UNIQUE INDEX `UNIQUE CONSTRAINTE1` ON myforecasts.registration_desc (tournament_id, user_id);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (1, 1);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (7, 1);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (9, 1);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (10, 1);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (11, 1);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (12, 1);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (15, 1);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (1, 2);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (1, 3);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (4, 3);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (1, 4);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (1, 5);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (1, 6);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (1, 7);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (1, 8);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (6, 8);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (7, 8);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (9, 8);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (1, 9);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (1, 10);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (2, 11);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (3, 11);
INSERT INTO myforecasts.registration_desc (tournament_id, user_id) VALUES (4, 11);