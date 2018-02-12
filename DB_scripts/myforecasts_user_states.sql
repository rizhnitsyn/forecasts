CREATE TABLE myforecasts.user_states
(
    user_state_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_state varchar(50) NOT NULL
);
CREATE UNIQUE INDEX user_state_name_uindex ON myforecasts.user_states (user_state);
INSERT INTO myforecasts.user_states (user_state) VALUES ('Администратор');
INSERT INTO myforecasts.user_states (user_state) VALUES ('Заблокирован');
INSERT INTO myforecasts.user_states (user_state) VALUES ('Зарегистрирован');
INSERT INTO myforecasts.user_states (user_state) VALUES ('Ожидание регистрации');