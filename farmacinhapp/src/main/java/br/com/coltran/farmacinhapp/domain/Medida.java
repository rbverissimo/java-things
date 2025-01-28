package br.com.coltran.farmacinhapp.domain;

import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Este domínio mapeia de forma estática se a medida de uma gramtura é em mg ou g.
 * O objeteivo deste mapeamento é facilitar nos cálculos das dosagens dos remédios.
 */
@Entity
@Table(name = "medidas")
public class Medida implements TableEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String descricao;

    private String abreviacao;

    @ManyToMany(targetEntity = Gramatura.class, cascade = CascadeType.ALL)
    @JoinTable(name = "medidas_gramaturas",
            joinColumns = @JoinColumn(name = "medida_id"),
            inverseJoinColumns = @JoinColumn(name = "gramatura_id")
    )
    @JsonBackReference
    private Set<Gramatura> gramaturas;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "America/Sao_Paulo")
    private ZonedDateTime dataCriacao;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "America/Sao_Paulo")
    private ZonedDateTime dataAlteracao;

    public Medida() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public Set<Gramatura> getGramaturas() {
        return gramaturas;
    }

    public void setGramaturas(Set<Gramatura> gramaturas) {
        this.gramaturas = gramaturas;
    }

    public ZonedDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(ZonedDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public ZonedDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(ZonedDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
