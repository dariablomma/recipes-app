-- liquibase formatted sql

-- changeset daria:002-create-categories-table
CREATE TABLE categories (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(200) NOT NULL,
user_id BIGINT NOT NULL,
created_at TIMESTAMP WITH TIME ZONE NOT NULL,
updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
deleted_at TIMESTAMP WITH TIME ZONE,

CONSTRAINT fk_category_user FOREIGN KEY (user_id) REFERENCES users(id)
);
-- rollback DROP TABLE categories;

-- changeset daria:002-create-categories-table-add-index
CREATE INDEX idx_categories_user_id ON categories(user_id);
CREATE INDEX idx_categories_deleted_at ON categories(deleted_at) WHERE deleted_at IS NOT NULL;
CREATE UNIQUE INDEX idx_categories_user_name ON categories(user_id, name) WHERE deleted_at IS NULL;
-- rollback DROP INDEX idx_categories_user_id, idx_categories_deleted_at, idx_categories_user_name;