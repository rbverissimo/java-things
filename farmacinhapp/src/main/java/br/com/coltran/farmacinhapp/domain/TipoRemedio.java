package br.com.coltran.farmacinhapp.domain;

import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Este domínio mapeia de forma estática se um remédio é do tipo: comprimido ou gotas
 */
@Entity
@Table(name = "tipos_remedio")
public class TipoRemedio implements TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String tipo;
    private String descricao;

    @OneToMany(targetEntity = Remedio.class, mappedBy = "tipoRemedio", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Remedio> remedios;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "America/Sao_Paulo")
    private ZonedDateTime dataCriacao;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "America/Sao_Paulo")
    private ZonedDateTime dataAlteracao;

    public TipoRemedio() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
}
