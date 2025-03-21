package br.com.coltran.farmacinhapp.domain;


import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "pacientes")
public class Paciente implements TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Digite um nome válido para o paciente")
    private String nome;
    private String sobrenome;
    private int idade;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Informe a data de nascimento do paciente")
    @Past(message = "A data de nascimento deve ser uma data anterior à data de hoje")
    private LocalDate dataNascimento;

    @Column(name = "desc_tratamento", length = 65535)
    private String descTratamento;

    @OneToMany(targetEntity = Farmacia.class, mappedBy = "paciente", fetch = FetchType.EAGER)
    private List<Farmacia> farmacias;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dataCriacao;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dataAlteracao;


    public Paciente() {
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<Farmacia> getFarmacias() {
        return farmacias;
    }

    public void setFarmacias(List<Farmacia> farmacias) {
        this.farmacias = farmacias;
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

    public String getDescTratamento() {
        return descTratamento;
    }

    public void setDescTratamento(String descTratamento) {
        this.descTratamento = descTratamento;
    }
}
