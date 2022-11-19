create sequence hibernate_sequence start 4 increment 1;

create table authors (
    id int8 not null,
    nickname varchar(255) not null,
    short_information varchar(2048),
    date_of_birth timestamp,
    date_create timestamp,
    primary key (id)
);

create table my_users (
    id int8 not null,
    active boolean not null,
    username varchar(255) not null,
    password varchar(255) not null,
    author_id int8,
    primary key (id)
);

create table posts (
    id int8 not null,
    subject_post varchar(255) not null,
    anons_post varchar(255),
    full_text_post varchar(2048),
    author_id int8,
    primary key (id)
);

create table tags (
    id int8 not null,
    name varchar(255) not null,
    short_description varchar(2048),
    primary key (id)
);

create table posts_tags (
    post_id int8 not null,
    tags_id int8 not null,
    primary key (post_id, tags_id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

alter table if exists my_users
    add constraint user_author_fk
    foreign key (author_id) references authors;

alter table if exists posts
    add constraint post_author_fk
    foreign key (author_id) references authors;

alter table if exists posts_tags
    add constraint post_tags_tag_fk
    foreign key (tags_id) references tags;

alter table if exists posts_tags
    add constraint post_tags_post_fk
    foreign key (post_id) references posts;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references my_users;