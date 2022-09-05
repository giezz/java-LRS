CREATE TABLE client
(
    id          SERIAL PRIMARY KEY,
    client_name TEXT NOT NULL
);

TRUNCATE product CASCADE;
CREATE TABLE product
(
    id           SERIAL PRIMARY KEY,
    product_name TEXT NOT NULL,
    cost         REAL
        CONSTRAINT valid_cost CHECK ( cost >= 0 )
);

CREATE TABLE client_product
(
    client_id  INTEGER REFERENCES client (id),
    product_id INTEGER REFERENCES product (id)
);

CREATE TABLE transaction_history
(
    id         SERIAL PRIMARY KEY,
    client_id  INTEGER REFERENCES client (id),
    product_id INTEGER REFERENCES product (id),
    cost       REAL
        CONSTRAINT valid_cost CHECK ( cost >= 0 )
);

CREATE OR REPLACE FUNCTION transaction_insert() RETURNS TRIGGER AS
$$
BEGIN
    INSERT INTO transaction_history(id, client_id, product_id, cost)
    VALUES (DEFAULT, NEW.client_id, NEW.product_id, (SELECT cost FROM product WHERE product.id = NEW.product_id));
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- DROP TRIGGER t_transaction_insert ON client_product;
CREATE TRIGGER t_transaction_insert
    AFTER INSERT
    ON client_product
    FOR EACH ROW
EXECUTE FUNCTION transaction_insert();

select c1_0.id, c1_0.client_name
from client c1_0
where c1_0.id = 5;
select p1_0.id,p1_0.cost,p1_0.product_name
from product p1_0
where p1_0.id=5;
select p1_0.client_id, p1_1.id, p1_1.cost, p1_1.product_name
from client_product p1_0
         join product p1_1 on p1_1.id = p1_0.product_id
where p1_0.client_id=5;