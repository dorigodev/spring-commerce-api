CREATE TABLE cart(
    id SERIAL PRIMARY KEY UNIQUE ,
    user_id INTEGER,
    CONSTRAINT fk_user_cart FOREIGN KEY(user_id) REFERENCES users(id)
)