package br.com.coltran.farmacinhapp.controllers.web.dto;

import br.com.coltran.farmacinhapp.validators.abstractions.DtNasctoUsuario;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

public class UpdateUserDto implements Serializable {

    @NotBlank(message = "O nome do usuário não pode estar em branco.")
    private String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @DtNasctoUsuario
    private LocalDate dataNascimento;

    private String biografia;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}
