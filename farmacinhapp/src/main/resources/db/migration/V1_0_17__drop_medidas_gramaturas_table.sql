DROP TABLE MEDIDAS_GRAMATURAS;

ALTER TABLE GRAMATURAS ADD COLUMN MEDIDA_ID BIGINT NOT NULL;

ALTER TABLE GRAMATURAS ADD CONSTRAINT FK_GRTR_MEDIDA FOREIGN KEY(MEDIDA_ID) REFERENCES MEDIDAS(ID);
