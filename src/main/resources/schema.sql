DROP TABLE IF EXISTS product;

CREATE TABLE Product (
  id IDENTITY NOT NULL PRIMARY KEY,
  type VARCHAR(250) NOT NULL,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) DEFAULT NULL
);