package br.com.coltran.farmacinhapp.controllers.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ShareFarmacia implements Serializable {

    @NotNull(message = "O e-mail do usuário é obrigatório")
    @JsonProperty("email")
    private String emailUsuario;

    @NotNull(message = "O nome da farmácia é obrigatório")
    @JsonProperty("farmacia")
    private String nomeFarmacia;

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNomeFarmacia() {
        return nomeFarmacia;
    }

    public void setNomeFarmacia(String nomeFarmacia) {
        this.nomeFarmacia = nomeFarmacia;
    }
}
