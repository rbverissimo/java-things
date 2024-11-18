package br.com.coltran.farmacinhapp.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "remedios")
public class Remedio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String informacoesAdicionais;
    private Integer doses;
    private Integer consumoDiario;
    private LocalDateTime dataInicioTratamento;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;

    @ManyToOne(targetEntity = Gramatura.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Gramatura gramatura;

    @ManyToOne(targetEntity = Farmacia.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Farmacia farmacia;

    public Remedio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
