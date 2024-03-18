CREATE TABLE order_product(

    order_id BIGSERIAL,
    product_id BIGSERIAL,
    CONSTRAINT order_product_pk PRIMARY KEY (order_id, product_id),
    CONSTRAINT FK_order
        FOREIGN KEY (order_id) REFERENCES order_table(id),
    CONSTRAINT FK_product
        FOREIGN KEY (product_id) REFERENCES product(id)
);