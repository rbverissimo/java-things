package br.com.coltran.farmacinhapp.domain;

import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Este domínio mapeia de forma estática se a medida de uma gramtura é em mg ou g.
 * O objeteivo deste mapeamento é facilitar nos cálculos das dosagens dos remédios.
 */
@Entity
@Table(name = "medidas")
public class Medida implements TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String descricao;

    @ManyToMany(targetEntity = Gramatura.class, cascade = CascadeType.ALL)
    @JoinTable(name = "medidas_gramaturas",
            joinColumns = @JoinColumn(name = "medida_id"),
            inverseJoinColumns = @JoinColumn(name = "gramatura_id")
    )
    private Set<Gramatura> gramaturas;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dataCriacao;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
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
