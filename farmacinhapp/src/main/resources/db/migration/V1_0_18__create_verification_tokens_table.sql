CREATE TABLE IF NOT EXISTS verification_tokens(
    id BIGINT NOT NULL AUTO_INCREMENT,
    token CHARACTER VARYING(127) NOT NULL,
    expired_at TIMESTAMP WITH TIME ZONE NOT NULL,
    verified_at TIMESTAMP WITH TIME ZONE,
    user_id BIGINT NOT NULL,

    FOREIGN KEY(user_id) REFERENCES users(id),
    PRIMARY KEY(id)
);