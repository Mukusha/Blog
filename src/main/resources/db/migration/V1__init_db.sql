create sequence hibernate_sequence start 5 increment 1;

create table authors
(
    id                bigserial primary key,
    nickname          varchar(255) not null,
    short_information varchar(2048),
    date_of_birth     timestamp,
    date_create       timestamp
);

create table my_users
(
    id        bigserial primary key,
    active    boolean      not null,
    username  varchar(255) not null,
    password  varchar(255) not null,
    author_id int8 references authors
);

create table posts
(
    id             bigserial primary key,
    subject_post   varchar(255) not null,
    anons_post     varchar(255),
    full_text_post varchar(2048),
    author_id      int8 references authors on delete cascade
);

create table tags
(
    id                bigserial primary key,
    name              varchar(255) not null,
    short_description varchar(2048)
);

create table posts_tags
(
    post_id int8 references posts,
    tags_id int8 references tags
);

create table user_role
(
    user_id int8 references my_users not null,
    roles   varchar(255)
);