insert into authors (id, nickname,short_information, date_of_birth, date_create)
values (1, 'admin','control', '1988-12-22 00:00:00', '2015-06-07 10:01:10'),
       (2, 'profileAdmin','','1988-12-22 00:00:00','2015-06-07 10:01:10'),
       (3, 'profileAuthor','','1995-01-24 00:00:00','2020-12-09 10:01:10'),
       (4, 'profileUserNotPost','','2001-05-14 00:00:00','2021-11-19 10:01:10');

insert into users (id, active, username, password, author_id)
values (1, true, 'admin', 'password', 1),
       (2,true, 'ad', '123',2 ),
       (3,true, 'q', '1',3 ),
       (4,true, 'w', '2',4 );

insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN'),
       (2, 'USER'), (2, 'ADMIN'),
       (3, 'USER'),
       (4, 'USER');

insert into posts (id,subject_post, anons_post, full_text_post, author_id)
values (1,  'Пост 1. О кирпиче',
                 'Анонс 1: Он нашел свободные кирпичи',
                 'Полный текст 1: Он решил изучить ситуацию.',
                 3 ),
       (2, 'Пост 2. Утро.',
                 'Анонс 2: На стройке много кирпичей',
                 'Полный текст 2: Смеркалось',
                 3 ),
       (3,  'Правила ведения страницы',
                 'Это важно знать',
                 'Ведем себя вежливо, иначе я вашу страницу удалю) Кирпич',
                 2 );

insert into tags (id, name,short_description)
values (1, 'Записки охотника за кирпичами', 'Рассказы по мотивам М.Задорного.'),
       (2, 'all', 'все возможные темы'),
       (3, 'правила', 'все возможные правила админа');

insert into posts_tags (post_id, tags_id)
values (1, 1), (1, 2),
       (2, 1),
       (3, 2), (3, 3);