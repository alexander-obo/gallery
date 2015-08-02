DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;

CREATE TABLE users (
    id SERIAL,
    user_name VARCHAR(20) PRIMARY KEY,
    user_password VARCHAR(20) NOT NULL
);
CREATE TABLE user_roles (
    id SERIAL,
    user_name varchar(20) NOT NULL,
    role_name varchar(20) NOT NULL,
    PRIMARY KEY (user_name, role_name)
);

INSERT INTO users (user_name, user_password) VALUES ('admin', 'admin');
INSERT INTO user_roles (user_name, role_name) VALUES ('admin', 'admin');
