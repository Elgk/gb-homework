create table products
(
    id         bigserial primary key,
    title      varchar(255),
    price      int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into products (title, price)
values ('Bread', 24),
       ('Milk', 65),
       ('Cheese', 700),
       ('Potato', 45),
       ('Tomato', 120),
       ('Butter', 600)
       ('Apple', 80);

create table order_items
(
    id             bigserial primary key,
    title          varchar(255),
    quantity       int,
    price_per_item int,
    price          int
);

create table users
(
    id       bigserial,
    username varchar(30) not null,
    password varchar(80) not null,
    email    varchar(50) unique,
    primary key (id)
);

create table roles
(
    id   serial,
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

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$10$RSV.pEkJbaHOlKR7WId2KeyrVC.m9mlyXbc8JQfzQhcKKYNiZpHoa', 'user@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (1, 2);