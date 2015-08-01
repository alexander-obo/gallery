DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id SERIAL,
    name VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(20) NOT NULL,
    roles VARCHAR(40) NOT NULL
);
INSERT INTO users (name, password, roles) VALUES ('admin', 'admin', 'admin,user');
