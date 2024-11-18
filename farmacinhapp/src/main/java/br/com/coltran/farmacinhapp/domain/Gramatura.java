package br.com.coltran.farmacinhapp.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity()
@Table(name = "gramaturas")
public class Gramatura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String principioAtivo;
    private Double valorGramatura;

    @ManyToMany(targetEntity = Medida.class, mappedBy = "gramaturas")
    private Set<Medida> medidas;

    @OneToMany(targetEntity = Remedio.class, mappedBy = "gramatura", fetch = FetchType.EAGER)
    private List<Remedio> remedios;

    public Gramatura() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Remedio> getRemedios() {
        return remedios;
    }

    public void setRemedios(List<Remedio> remedios) {
        this.remedios = remedios;
    }
}
