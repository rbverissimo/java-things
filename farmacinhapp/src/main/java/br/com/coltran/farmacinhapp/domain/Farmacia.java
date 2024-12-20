package br.com.coltran.farmacinhapp.domain;

import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;
import br.com.coltran.farmacinhapp.security.domain.User;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "farmacias")
public class Farmacia implements TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Digite um nome válido para a farmácia")
    private String nome;

    @NotEmpty(message = "Digite uma descrição válida para a farmácia")
    private String bio;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime dataCriacao;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime  dataAlteracao;

    @Valid
    @ManyToOne(targetEntity = Paciente.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Paciente paciente;

    @OneToMany(targetEntity = Remedio.class, mappedBy = "farmacia", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Remedio> remedios;

    @OneToMany(targetEntity = Lembrete.class, mappedBy = "farmacia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Lembrete> lembretes;

    @ManyToMany(mappedBy = "farmacias")
    private Set<User> users;

    public Farmacia() {
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
}
