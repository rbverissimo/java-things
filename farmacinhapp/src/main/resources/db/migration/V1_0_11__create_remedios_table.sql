CREATE TABLE remedios(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome CHARACTER VARYING(127) NOT NULL,
    informacoes_adicionais CHARACTER VARYING,
    doses INTEGER NOT NULL,
    consumo_diario INTEGER NOT NULL,
    data_inicio_tratamento TIMESTAMP WITH TIME ZONE NOT NULL,
    data_criacao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    data_alteracao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    tipo_remedio_id BIGINT NOT NULL,
    farmacia_id BIGINT NOT NULL,

    FOREIGN KEY(tipo_remedio_id) references tipos_remedio(id),
    FOREIGN KEY(farmacia_id) references farmacias(id),
    PRIMARY KEY(ID)
);