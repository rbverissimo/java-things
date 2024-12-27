package br.com.coltran.farmacinhapp.domain;

import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "remedios")
public class Remedio implements TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String informacoesAdicionais;
    private Integer doses;
    private Integer consumoDiario;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dataInicioTratamento;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dataCriacao;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dataAlteracao;

    @ManyToOne(targetEntity = Gramatura.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Gramatura gramatura;

    @ManyToOne(targetEntity = Farmacia.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Farmacia farmacia;

    public Remedio() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public Integer getDoses() {
        return doses;
    }

    public void setDoses(Integer doses) {
        this.doses = doses;
    }

    public Integer getConsumoDiario() {
        return consumoDiario;
    }

    public void setConsumoDiario(Integer consumoDiario) {
        this.consumoDiario = consumoDiario;
    }

    public ZonedDateTime getDataInicioTratamento() {
        return dataInicioTratamento;
    }

    public void setDataInicioTratamento(ZonedDateTime dataInicioTratamento) {
        this.dataInicioTratamento = dataInicioTratamento;
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

    public Gramatura getGramatura() {
        return gramatura;
    }

    public void setGramatura(Gramatura gramatura) {
        this.gramatura = gramatura;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }
}
