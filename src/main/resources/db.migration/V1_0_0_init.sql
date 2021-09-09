create table products
(
    id    bigserial primary key,
    title  text,
    price int
);
insert into products(title, price)
values ('potato', 1),
       ('tomato', 2),
       ('apple', 4);

create table client
(
    id    bigserial primary key,
    name  text
);
insert into client(name)
values ('Jack'),
       ('Tom'),
       ('Ann');

create table orders
(
    id        bigserial primary key,
    client_id int
);
insert into orders(client_id)
values ( 1),
       ( 2),
       ( 2),
       ( 3);

create table order_details
(
    id         bigserial primary key,
    order_id   int,
    title      text,
    price      int
)
insert into order_details(order_id, title, price)
values (1, 'potato', 1),
       (1, 'tomato', 2),
       (1, 'apple',  4),
       (2, 'tomato', 2),
       (3, 'tomato', 2),
       (3, 'apple',  3),
       (4, 'potato', 1);

