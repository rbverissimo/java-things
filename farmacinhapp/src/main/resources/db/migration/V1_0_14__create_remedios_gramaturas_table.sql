CREATE TABLE remedios_gramaturas(
    remedio_id BIGINT NOT NULL,
    gramatura_id BIGINT NOT NULL,
    FOREIGN KEY(remedio_id) references remedios(id),
    FOREIGN KEY(gramatura_id) references gramaturas(id),
    PRIMARY KEY(remedio_id, gramatura_id)
);