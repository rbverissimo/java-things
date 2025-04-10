CREATE TABLE IF NOT EXISTS farmacia_share_tokens(
    id BIGINT NOT NULL AUTO_INCREMENT,
    token CHARACTER VARYING(127) NOT NULL,
    expired_at TIMESTAMP WITH TIME ZONE,
    verified_at TIMESTAMP WITH TIME ZONE,
    user_id BIGINT NOT NULL,
    farmacia_id BIGINT NOT NULL,

    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(farmacia_id) REFERENCES farmacias(id),
    PRIMARY KEY(id)
);