CREATE TABLE product_category(
    product_id INTEGER,
    category_id INTEGER,
    CONSTRAINT fk_product_category_product FOREIGN KEY(product_id) REFERENCES product(id),
    CONSTRAINT fk_product_category_category FOREIGN KEY(category_id) REFERENCES category(id)

)