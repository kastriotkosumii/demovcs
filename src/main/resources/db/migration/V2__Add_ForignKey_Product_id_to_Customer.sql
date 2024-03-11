ALTER TABLE Product
ADD CONSTRAINT fk_customer_id
FOREIGN KEY (customer_id)
REFERENCES customer(id);
