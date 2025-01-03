CREATE TABLE medidas_gramaturas (
  medida_id BIGINT NOT NULL,
  gramatura_id BIGINT NOT NULL,
  FOREIGN KEY (medida_id) REFERENCES medidas(id),
  FOREIGN KEY (gramatura_id) REFERENCES gramaturas(id),
  PRIMARY KEY (medida_id, gramatura_id)
);