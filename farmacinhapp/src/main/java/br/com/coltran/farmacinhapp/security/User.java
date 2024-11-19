package br.com.coltran.farmacinhapp.security;

import br.com.coltran.farmacinhapp.domain.Farmacia;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String nome;
    public String email;
    public String senha;

    @OneToMany(targetEntity = Farmacia.class, mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Farmacia> farmacias;

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Farmacia> getFarmacias() {
        return farmacias;
    }

    public void setFarmacias(Set<Farmacia> farmacias) {
        this.farmacias = farmacias;
    }
}
