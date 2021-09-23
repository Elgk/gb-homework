create table security.users
(
    id       bigserial,
    username varchar(30) not null,
    password varchar(80) not null,
    email    varchar(50) unique,
    score    int,
    primary key (id)
);

create table security.roles
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE security.users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into security.users (username, password, email, score)
values ('user'application.properties, '$2a$12$A.BjRipNu5GWESYrQ/IOfO.jOAs4PPcFqNmFJ7RsatV0JpBw805ky', 'user@email.com',0),
       ('admin', '$2a$12$vimdE9.Z38uCcSFgUfK9Z.yBlVlP37f6gjyKRjmVhsnt8DjFW66J2', 'admin@email.com',0);

insert into security.roles (name) values ('admin'),('user');

insert into security.users_roles (user_id, role_id) values (1,2), (2, 1);

