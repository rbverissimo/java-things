package br.com.coltran.farmacinhapp.domain;

import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "lembretes")
public class Lembrete implements TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String conteudo;

    @ManyToOne(targetEntity = Farmacia.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Farmacia farmacia;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dataCriacao;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dataAlteracao;

    public Lembrete() {
    }

    @Override
    public ZonedDateTime getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public void setDataCriacao(ZonedDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public ZonedDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    @Override
    public void setDataAlteracao(ZonedDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
