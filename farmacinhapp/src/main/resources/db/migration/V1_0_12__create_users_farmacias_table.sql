CREATE TABLE users_farmacias(
    user_id BIGINT NOT NULL,
    farmacia_id BIGINT NOT NULL,
    FOREIGN KEY(user_id) references users(id),
    FOREIGN KEY(farmacia_id) references farmacias(id),
    PRIMARY KEY(user_id, farmacia_id)
);