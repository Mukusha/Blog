insert into authors (id, nickname, date_of_birth, date_create)
values (1, 'admin', '1988-12-22 00:00:00', '2015-06-07 10:01:10');

insert into users (id, active, username, password, author_id)
values (1, true, 'admin', 'password', 1);

insert into user_role (user_id, roles)
values (1, 'USER'),
       (1, 'ADMIN')