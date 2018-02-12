CREATE TABLE myforecasts.teams
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    team_name varchar(30) NOT NULL
);
CREATE UNIQUE INDEX team_name ON myforecasts.teams (team_name);
INSERT INTO myforecasts.teams (team_name) VALUES ('Австрия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Албания');
INSERT INTO myforecasts.teams (team_name) VALUES ('Англия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Аргентина');
INSERT INTO myforecasts.teams (team_name) VALUES ('Бельгия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Бразилия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Венгрия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Германия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Греция');
INSERT INTO myforecasts.teams (team_name) VALUES ('Египет');
INSERT INTO myforecasts.teams (team_name) VALUES ('Иран');
INSERT INTO myforecasts.teams (team_name) VALUES ('Ирландия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Исландия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Испания');
INSERT INTO myforecasts.teams (team_name) VALUES ('Италия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Колумбия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Корея');
INSERT INTO myforecasts.teams (team_name) VALUES ('Коста-Рика');
INSERT INTO myforecasts.teams (team_name) VALUES ('Мексика');
INSERT INTO myforecasts.teams (team_name) VALUES ('Нигерия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Панама');
INSERT INTO myforecasts.teams (team_name) VALUES ('Польша');
INSERT INTO myforecasts.teams (team_name) VALUES ('Португалия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Россия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Румыния');
INSERT INTO myforecasts.teams (team_name) VALUES ('Саудовская Аравия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Сев. Ирландия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Сербия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Словакия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Турция');
INSERT INTO myforecasts.teams (team_name) VALUES ('Украина');
INSERT INTO myforecasts.teams (team_name) VALUES ('Уругвай');
INSERT INTO myforecasts.teams (team_name) VALUES ('Уэльс');
INSERT INTO myforecasts.teams (team_name) VALUES ('Франция');
INSERT INTO myforecasts.teams (team_name) VALUES ('Хорватия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Чехия');
INSERT INTO myforecasts.teams (team_name) VALUES ('Швейцария');
INSERT INTO myforecasts.teams (team_name) VALUES ('Швеция');
INSERT INTO myforecasts.teams (team_name) VALUES ('Япония');