create table if not exists products
( id    bigserial primary key,
  name varchar(255),
  price int,
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp
);

insert into products (name, price)
values ('milk', 56),
       ('potato', 60),
       ('tomato', 130),
       ('onion', 35),
       ('cucumber', 90),
       ('radish', 80),
       ('pineapple', 300),
       ('carrot', 50),
       ('pepper', 250),
       ('plump', 230),
       ('banana', 89),
       ('bread', 41),
       ('eggs', 99),
       ('yogurt', 89),
       ('lemon', 140),
       ('apple', 150);

