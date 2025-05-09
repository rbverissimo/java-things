CREATE TABLE IF NOT EXISTS users_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (role_id) REFERENCES roles(id),
  PRIMARY KEY (user_id, role_id)
);