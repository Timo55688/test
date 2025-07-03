DROP TABLE IF EXISTS products;

CREATE TABLE IF NOT EXISTS products (
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(20) NOT NULL,
    price INT         NOT NULL,
    stock INT         NOT NULL
);
