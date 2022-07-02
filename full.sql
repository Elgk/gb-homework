BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int );
INSERT INTO products (id, title, price) VALUES
                                      (1,'milk', 100),
                                      (2,'sugar', 70),
                                      (3,'cheese', 1200),
                                      (4,'tomato', 150),
                                      (5,'potato', 60);

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (id, name) VALUES
                              (1,'Bob'),
                              (2,'Jack'),
                              (3,'Ann');

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders (id bigserial PRIMARY KEY, customer_id bigint, product_id bigint, price int, quantity int, order_date date default current_date,
                              FOREIGN KEY (customer_id) REFERENCES customers (id), FOREIGN KEY (product_id) REFERENCES products (id) );
INSERT INTO orders (customer_id, product_id, price, quantity) VALUES
                                                                      (1, 1, 90, 1),
                                                                      (1, 2, 70, 3),
                                                                      (2, 1, 100, 2),
                                                                      (2, 3, 945, 1),
                                                                      (3, 5, 40, 2);
COMMIT;