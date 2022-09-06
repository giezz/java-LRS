DROP TABLE client cascade;
CREATE TABLE client
(
    id          SERIAL PRIMARY KEY,
    client_name TEXT NOT NULL
);

DROP TABLE product cascade;
CREATE TABLE product
(
    id           SERIAL PRIMARY KEY,
    product_name TEXT NOT NULL,
    cost         REAL
        CONSTRAINT valid_cost CHECK ( cost >= 0 )
);


DROP TABLE order_info;
CREATE TABLE order_info
(
    id         SERIAL PRIMARY KEY,
    client_id  INTEGER REFERENCES client (id),
    product_id INTEGER REFERENCES product (id),
    order_cost REAL
        CONSTRAINT valid_cost CHECK ( order_cost >= 0 )
        DEFAULT NULL
);

insert into product
values (default, 'p0', 1);
insert into client
values (default, 'c0');
insert into order_info (client_id, product_id)
values (1, 1);

SELECT * FROM order_info WHERE

CREATE OR REPLACE FUNCTION order_info_insert() RETURNS TRIGGER AS
$$
BEGIN
    UPDATE order_info
    SET order_cost = (SELECT cost FROM product WHERE product.id = NEW.product_id)
    WHERE id = NEW.id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER t_order_info_insert_insert ON order_info;
CREATE TRIGGER t_order_info_insert_insert
    AFTER INSERT
    ON order_info
    FOR EACH ROW
EXECUTE FUNCTION order_info_insert();
