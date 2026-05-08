CREATE TABLE image (
    id BIGINT NOT NULL AUTO_INCREMENT,
    origin_name VARCHAR(256) NOT NULL,
    stored_name VARCHAR(256) NOT NULL,
    file_path VARCHAR(256) NOT NULL,
    extension VARCHAR(8),
    status VARCHAR(16) NOT NULL DEFAULT 'TEMPORARY',
    file_size BIGINT,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),

    post_id BIGINT,
    user_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT uk_image_stored_name UNIQUE (stored_name)
)