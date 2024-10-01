package org.example.model;

import org.example.abstractions.IESocialTotalizador;

public class ESocialTotalizadorINSS implements IESocialTotalizador {

    private final String TIPO_EVENTO = "TOTALIZADOR_INSS";
    private final String NOME_TOTALIZADOR = "Totalizador INSS";

    public String getTipoEvento() {
        return TIPO_EVENTO;
    }

    public String getNomeTotalizador() {
        return NOME_TOTALIZADOR;
    }

}
