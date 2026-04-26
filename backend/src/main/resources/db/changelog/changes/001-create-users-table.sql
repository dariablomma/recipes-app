-- liquibase formatted sql

-- changeset daria:001-create-users-table
CREATE TABLE users (
id BIGSERIAL PRIMARY KEY,
user_name VARCHAR(200) NOT NULL,
password VARCHAR(255) NOT NULL,
email VARCHAR(200) NOT NULL UNIQUE,
first_name VARCHAR(200),
last_name VARCHAR(200),
created_at TIMESTAMP WITH TIME ZONE NOT NULL,
updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
deleted_at TIMESTAMP WITH TIME ZONE
);

-- rollback DROP TABLE users;