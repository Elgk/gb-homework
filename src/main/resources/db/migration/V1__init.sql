create table users
(
    id       bigserial,
    username varchar(30) not null,
    password varchar(80) not null,
    email    varchar(50) unique,
    score    int,
    primary key (id)
);

create table roles
(
    id   bigserial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into users (username, password, email, score)
values ('user', '$2a$12$A.BjRipNu5GWESYrQ/IOfO.jOAs4PPcFqNmFJ7RsatV0JpBw805ky', 'user@email.com', 0),
       ('admin', '$2a$12$vimdE9.Z38uCcSFgUfK9Z.yBlVlP37f6gjyKRjmVhsnt8DjFW66J2', 'admin@email.com', 0);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('UPDATE_SCORE');

insert into users_roles (user_id, role_id)
values (1,1),
       (2, 2),
       (2, 3);

