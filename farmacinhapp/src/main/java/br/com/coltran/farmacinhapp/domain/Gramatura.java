package br.com.coltran.farmacinhapp.domain;

import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "gramaturas")
public class Gramatura implements TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Nullable
    private String principioAtivo;

    private Double valorGramatura;

    @ManyToMany(targetEntity = Medida.class, mappedBy = "gramaturas")
    private Set<Medida> medidas;

    @ManyToMany(targetEntity = Remedio.class, mappedBy = "gramaturas", fetch = FetchType.LAZY)
    private Set<Remedio> remedios;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dataCriacao;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dataAlteracao;

    public Gramatura() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public Double getValorGramatura() {
        return valorGramatura;
    }

    public void setValorGramatura(Double valorGramatura) {
        this.valorGramatura = valorGramatura;
    }

    public Set<Medida> getMedidas() {
        return medidas;
    }

    public void setMedidas(Set<Medida> medidas) {
        this.medidas = medidas;
    }

    public Set<Remedio> getRemedios() {
        return remedios;
    }

    public void setRemedios(Set<Remedio> remedios) {
        this.remedios = remedios;
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
