DROP TABLE IF EXISTS users_pictures;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL,
    user_name VARCHAR(20),
    user_password VARCHAR(20) NOT NULL,
    PRIMARY KEY (user_name)
);
CREATE TABLE user_roles (
    id SERIAL,
    user_name varchar(20),
    role_name varchar(20),
    PRIMARY KEY (user_name, role_name),
    FOREIGN KEY (user_name) REFERENCES users(user_name)
    ON DELETE CASCADE
);
CREATE TABLE users_pictures (
    id SERIAL,
    picture_name VARCHAR(60) NOT NULL,
    uploader_name VARCHAR(20) NOT NULL,
    picture MEDIUMBLOB NOT NULL,
    thumbnail MEDIUMBLOB NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (uploader_name) REFERENCES users(user_name)
    ON DELETE CASCADE
);

INSERT INTO users (user_name, user_password) VALUES ('admin', 'admin');
INSERT INTO user_roles (user_name, role_name) VALUES ('admin', 'admin');
