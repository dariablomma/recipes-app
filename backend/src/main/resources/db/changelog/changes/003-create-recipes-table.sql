-- liquibase formatted sql

-- changeset daria:003-create-recipes-table
CREATE TABLE recipes (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(200) NOT NULL,
                         external_link VARCHAR(500),
                         description VARCHAR(2000),
                         category_id BIGINT NOT NULL,
                         user_id BIGINT NOT NULL,
                         created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                         updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
                         deleted_at TIMESTAMP WITH TIME ZONE,

                         CONSTRAINT fk_recipe_category FOREIGN KEY (category_id) REFERENCES categories(id),
                         CONSTRAINT fk_recipe_user FOREIGN KEY (user_id) REFERENCES users(id)
);
-- rollback DROP TABLE recipes;

-- changeset daria:003-create-recipes-table-add-indexes
CREATE INDEX idx_recipes_category_id ON recipes(category_id);
CREATE INDEX idx_recipes_user_id ON recipes(user_id);
CREATE INDEX idx_recipes_deleted_at ON recipes(deleted_at) WHERE deleted_at IS NOT NULL;

-- rollback DROP INDEX idx_recipes_category_id, idx_recipes_user_id, idx_recipes_deleted_at;
