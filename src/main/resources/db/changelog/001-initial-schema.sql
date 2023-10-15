--liquibase formatted sql

--changeset Kate:1 runOnChange: true
CREATE TABLE IF NOT EXISTS users (
    id serial PRIMARY KEY,
    username TEXT,
    email TEXT,
    password TEXT
);
--changeset Kate:2 runOnChange: true
CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    role TEXT
);

INSERT INTO roles(id, role)
VALUES (1, 'ROLE_USER'),
       (2, 'ADMIN');

ALTER TABLE users ADD COLUMN user_role int;
ALTER TABLE users ADD CONSTRAINT user_role_fk FOREIGN KEY(user_role)
REFERENCES roles (id);


