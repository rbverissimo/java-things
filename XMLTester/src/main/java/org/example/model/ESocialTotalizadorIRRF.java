package org.example.model;

import org.example.abstractions.IESocialTotalizador;

public class ESocialTotalizadorIRRF implements IESocialTotalizador {

    private final String TIPO_EVENTO = "TOTALIZADOR_IRRF";
    private final String NOME_TOTALIZADOR = "Totalizador IRRF";

    public String getTipoEvento() {
        return TIPO_EVENTO;
    }

    public String getNomeTotalizador() {
        return NOME_TOTALIZADOR;
    }

}
