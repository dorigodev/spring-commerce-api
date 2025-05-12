CREATE TABLE product(
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    description text,
    price DECIMAL(15,2) NOT NULL,
    imageUrl varchar(255),
    stock_quantity INTEGER
)