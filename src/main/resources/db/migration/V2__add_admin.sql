insert into authors (id, nickname, short_information,  date_of_birth, date_create)
values (0, 'admin','', '1988-12-22 00:00:00', '2015-06-07 10:01:10');

insert into users (id, active, username, password, author_id)
values (0, true, 'admin', 'password', 0);

insert into user_role (user_id, roles)
values (0, 'USER'),
       (0, 'ADMIN');