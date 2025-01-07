CREATE TABLE medidas(
    id BIGINT NOT NULL AUTO_INCREMENT,
    descricao CHARACTER VARYING(64),
    abreviacao CHARACTER VARYING(6) NOT NULL,
    data_criacao TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY(id)
);


INSERT INTO MEDIDAS(descricao, abreviacao) values('gramas', 'g');
INSERT INTO MEDIDAS(descricao, abreviacao) values('miligramas', 'mg');
INSERT INTO MEDIDAS(descricao, abreviacao) values('microgramas', 'mcg');
INSERT INTO MEDIDAS(descricao, abreviacao) values('mililitros', 'ml');