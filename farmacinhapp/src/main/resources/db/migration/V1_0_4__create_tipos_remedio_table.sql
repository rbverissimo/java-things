CREATE TABLE IF NOT EXISTS tipos_remedio (

    id BIGINT NOT NULL AUTO_INCREMENT,
    tipo CHARACTER VARYING NOT NULL,
    descricao CHARACTER VARYING(2112),
    data_criacao TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_alteracao TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY(id)
);

INSERT INTO TIPOS_REMEDIO(tipo, descricao) values ('Comprimidos', 'Tipo usado para remédios em que a unidade medição é o comprimido.');
INSERT INTO TIPOS_REMEDIO(tipo, descricao) values('Gotas', 'Tipo usado para remédios em que a unidade de medição é a quantidade de gotas.');
INSERT INTO TIPOS_REMEDIO(tipo, descricao) values('Inalação', 'Tipo usado para remédios em que a quantidade de medição é relativa à inalação do medicamento');
INSERT INTO TIPOS_REMEDIO(tipo, descricao) values('Aplicação', 'Tipo usado para remédios em que a quantidade de medição é a aplicação de dose sem uma medida específica. Caso de vacinas e outras aplicações injetadas');
INSERT INTO TIPOS_REMEDIO(tipo, descricao) values('Scoop', 'Tipo usado para remédios em que a quantidade de medição é a a medida de uma colher (scoop).');