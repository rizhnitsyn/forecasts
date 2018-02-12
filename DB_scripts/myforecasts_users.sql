CREATE TABLE myforecasts.users
(
    user_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name varchar(30) NOT NULL,
    second_name varchar(30) NOT NULL,
    email varchar(30) NOT NULL,
    user_state_id int(11) NOT NULL,
    login varchar(30) NOT NULL,
    password varchar(100) NOT NULL,
    CONSTRAINT user_stets_fk1 FOREIGN KEY (user_state_id) REFERENCES user_states (user_state_id)
);
CREATE UNIQUE INDEX email ON myforecasts.users (email);
CREATE UNIQUE INDEX users_login_uindex ON myforecasts.users (login);
CREATE INDEX user_stets_fk1_idx ON myforecasts.users (user_state_id);
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Андрей', 'Рижницын', 'ra@tut.by', 2, 'rizhnitsyn', '11111');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Сергей', 'Гусаков', 'sg@tut.by', 2, 'gusakov', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Роман', 'Жук', 'rzh@tut.by', 2, 'zhuk', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Никита', 'Щербич', 'nsch@tut.by', 2, 'scherbich', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Виталий', 'Назарян', 'vn@tut.by', 2, 'nazaryn', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Гайк', 'Бегларян', 'gb@tut.by', 2, 'asdsd', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Руслан', 'Акиловский', 'ar@tut.by', 2, 'asdsdsdsd', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Андрей', 'Иванов', 'ai@tut.by', 2, 'asdasdasdsad', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Камал', 'Бикулов', 'kb@tut.by', 2, 'asdasdsdsdsdsd', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Изабелла', 'Аванессова', 'izab@tut.by', 2, 'zxczxczxc', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('updated415002', 'Ivanov', 'test@gmail.com', 2, 'zxcxc', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Ivan', '129618', 'test37447@gmail.com', 2, 'vbxcvb', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Ivan', '851874', 'test78828@gmail.com', 3, 'cvbcv', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Тестик', 'Номер1', 'выаыва', 3, 'mnmy', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Иван ', 'Иван ', 'iv@bn.by', 2, 'nbvn', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Иваныч ', 'Иваныч ', 'ivb@bnb.by', 3, 'hjytrj', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Петя', 'Петя', 'ivbb@bnb.by', 2, 'ergr', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('иван', 'Ткачев', 'ert@tut.by', 2, 'dcvbed', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('иван', 'Ткачевич', 'ertm@tut.by', 1, 'cvbg', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Ivan', '180264', 'test4180@gmail.com', 1, 'fgjhr', '');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Андрей', 'Коньков', 'kon@tut.by', 1, 'konkov', '11111');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Иван', 'Петро', 'petro@tut.by', 3, 'petro', '11111');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Олег', 'Петрович', 'oleg@tut.by', 2, 'oleg', '11111');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Кирилл ', 'Вольски', 'vol@tut.by', 2, 'volskij', '11111');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Андрей', 'Копылов', 'kopi@tut.by', 2, 'kopilov', '11111');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Андре', 'Мелешкевич', 'mele@tut.by', 1, 'melesh', '11111');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Глеб', 'Маеев', 'makeev@tut.by', 2, 'ьфлуум', '11111');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('sdfdsf', 'sdfsdfdf', '@tut.nby', 3, 'sdfsdfsdfsdf', '11111');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('sdfsdf', 'sdfsdf', 'sdfsdf', 3, 'xcv', 'sfsdf');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('huhuh', 'hgfhfh', 'gihuhuh', 3, 'vefvervrvb', '11111');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('huhuhwerw', 'hgfhfhwere', 'gihuhuhwer', 3, 'dytvjbgywer', '11111ere');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('sdfsdf', 'gawghsvb', 'sdfhjwrtjjdf', 3, 'verwvqrv', 'asfe4tjtj');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('asdasd', 'asdfaasdad', 'sfsdfsdfsdf', 3, 'vgxcbbtebn', 'asdasdasdasd');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('tyu', 'tyu', 'tyu', 3, 'xcvcv', 'tyu');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('fgjfgjh', 'fjfgj', 'fgjhfgj', 3, 'fjfgj', 'af242e2abfda7079d2d56c8987013cd2');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('shshs', 'shshsh', 'shshshsh', 3, 'btetne4tnzxv', '2087d87063b08c54aa31012ed012bacb');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('sdfsdfsdf', 'sdfsdfsdfsdf', 'asdasd', 3, 'test', 'b59c67bf196a4758191e42f76670ceba');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('test1', 'kasjdaskdj', 'dasd', 3, 'test3', 'b0baee9d279d34fa1dfd71aadb908c3f');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('admin', 'admin', 'admin@tut.by', 4, 'admin', 'b0baee9d279d34fa1dfd71aadb908c3f');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('asdsd', 'sadasd', 'ra@dffdf.com', 1, 'cvdcwecwe', 'd29aaa0b9cd402b4bfe2395a805f9ada');
INSERT INTO myforecasts.users (first_name, second_name, email, user_state_id, login, password) VALUES ('Ivanchello', 'Petterson', 'asdasd123123@tut.by', 2, 'asdksadksdf', 'dasdjfidjfid');