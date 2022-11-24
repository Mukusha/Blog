insert into authors (id, nickname,short_information, date_of_birth, date_create)
values (1, 'admin','control', '1988-12-22 00:00:00', '2015-06-07 10:01:10');

insert into users (id, active, username, password, author_id)
values (1, true, 'admin', 'password', 1);

insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN');

insert into posts (id,subject_post, anons_post, full_text_post, author_id)
values  (1,  'Правила ведения страницы',
             'Это важно знать',
             'Ведем себя вежливо, иначе я вашу страницу удалю) ',
             1 );

insert into tags (id, name,short_description)
values  (1, 'правила', 'все возможные правила админа');

insert into posts_tags (post_id, tags_id)
values (1, 1);
