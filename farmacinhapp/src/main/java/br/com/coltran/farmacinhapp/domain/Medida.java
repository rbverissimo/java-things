package br.com.coltran.farmacinhapp.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medidas")
public class Medida {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descricao;

    @ManyToMany(targetEntity = Gramatura.class, cascade = CascadeType.ALL)
    @JoinTable(name = "remedios_gramaturas",
            joinColumns = @JoinColumn(name = "medida_id"),
            inverseJoinColumns = @JoinColumn(name = "gramatura_id")
    )
    private Set<Gramatura> gramaturas;

    public Medida() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
