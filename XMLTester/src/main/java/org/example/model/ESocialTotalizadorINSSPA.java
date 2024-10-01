package org.example.model;

import org.example.abstractions.IESocialTotalizador;

public class ESocialTotalizadorINSSPA implements IESocialTotalizador {

    private final String TIPO_EVENTO = "TOTALIZADOR_INSS_PA";
    private final String NOME_TOTALIZADOR = "Totalizador INSS Patronal";

    public String getTipoEvento() {
        return TIPO_EVENTO;
    }

    public String getNomeTotalizador() {
        return NOME_TOTALIZADOR;
    }
}
