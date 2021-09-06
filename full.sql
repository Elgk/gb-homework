BEGIN;

DROP TABLE IF EXISTS product CASCADE;
CREATE TABLE product (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO product (title, cost) VALUES
                                      ('box', 10),
                                      ('sphere', 20),
                                      ('maul', 100),
                                      ('door', 50),
                                      ('camera', 500);

DROP TABLE IF EXISTS client CASCADE;
CREATE TABLE client (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO client (name) VALUES
                              ('Bob'),
                              ('Jack'),
                              ('Ann');

DROP TABLE IF EXISTS client_products CASCADE;
CREATE TABLE client_products (id bigserial PRIMARY KEY, client_id bigint, product_id bigint, cost int, order int,
                              FOREIGN KEY (client_id) REFERENCES client (id), FOREIGN KEY (product_id) REFERENCES product (id) );
INSERT INTO client_products (client_id, product_id, cost, order ) VALUES
                                                                      (1, 1, 10, 1),
                                                                      (1, 2, 20, 1),
                                                                      (2, 1, 10, 2),
                                                                      (2, 3, 100, 2),
                                                                      (3, 5, 500, 3);
COMMIT;