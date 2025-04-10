package br.com.coltran.farmacinhapp.domain;

import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;
import br.com.coltran.farmacinhapp.security.domain.FarmaciaShareToken;
import br.com.coltran.farmacinhapp.security.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "farmacias")
public class Farmacia implements TableEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Digite um nome válido para a farmácia")
    private String nome;

    @NotEmpty(message = "Digite uma descrição válida para a farmácia")
    private String bio;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "America/Sao_Paulo")
    private ZonedDateTime dataCriacao;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "America/Sao_Paulo")
    private ZonedDateTime  dataAlteracao;

    @Valid
    @ManyToOne(targetEntity = Paciente.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Paciente paciente;

    @OneToMany(targetEntity = Remedio.class, mappedBy = "farmacia", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Remedio> remedios;

    @OneToMany(targetEntity = Lembrete.class, mappedBy = "farmacia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Lembrete> lembretes;

    @ManyToMany(mappedBy = "farmacias", fetch = FetchType.LAZY)
    private Set<User> users;

    @OneToMany(targetEntity = FarmaciaShareToken.class, mappedBy = "farmacia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<FarmaciaShareToken> farmaciaShareTokens;

    public Farmacia() {
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ZonedDateTime  getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(ZonedDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public ZonedDateTime  getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(ZonedDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Set<Remedio> getRemedios() {
        return remedios;
    }

    public void setRemedios(Set<Remedio> remedios) {
        this.remedios = remedios;
    }

    public Set<Lembrete> getLembretes() {
        return lembretes;
    }

    public void setLembretes(Set<Lembrete> lembretes) {
        this.lembretes = lembretes;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<FarmaciaShareToken> getFarmaciaShareTokens() {
        return farmaciaShareTokens;
    }

    public void setFarmaciaShareTokens(Set<FarmaciaShareToken> farmaciaShareTokens) {
        this.farmaciaShareTokens = farmaciaShareTokens;
    }
}
