package br.com.coltran.farmacinhapp.security.dto;

import br.com.coltran.farmacinhapp.security.validators.abstractions.PasswordMatch;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@PasswordMatch
public class UserRegDTO {


    @NotEmpty(message = "Digite o seu nome de usuário")
    @Size(min=3, max=255, message = "O tamanho do nome de usuário é inválido")
    private String username;

    @Email(message = "O e-mail não é válido")
    @NotEmpty(message = "Digite um e-mail")
    private String email;

    @NotEmpty(message = "Digite uma senha")
    private String password;

    private String passwordConfirm;

    public UserRegDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
