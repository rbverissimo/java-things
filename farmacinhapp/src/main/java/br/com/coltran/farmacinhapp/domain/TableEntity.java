package br.com.coltran.farmacinhapp.domain;

import java.time.ZonedDateTime;

public interface TableEntity {

    public void setDataCriacao(ZonedDateTime dataCriacao);
    public ZonedDateTime getDataCriacao();
    public void setDataAlteracao(ZonedDateTime dataAlteracao);
    public ZonedDateTime getDataAlteracao();

}
