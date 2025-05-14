CREATE TABLE cart_item(
    id INTEGER PRIMARY KEY,
    product_id INTEGER,
    quantity INTEGER CHECK ( quantity >= 1),
    cart_id INTEGER,
    CONSTRAINT fk_cart_item_product FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT fk_cart_item_cart FOREIGN KEY (cart_id) REFERENCES cart(id)
)