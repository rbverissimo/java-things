package br.com.coltran.farmacinhapp.controllers.api.dto;

import br.com.coltran.farmacinhapp.security.validators.abstractions.ValidPassword;
import br.com.coltran.farmacinhapp.validators.abstractions.ChangePasswordMatch;

import java.io.Serializable;

@ChangePasswordMatch
public class ChangePassword implements Serializable {

    private String senhaAtual;

    @ValidPassword
    private String novaSenha;
    private String novaSenhaConfirm;

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenhaConfirm() {
        return novaSenhaConfirm;
    }

    public void setNovaSenhaConfirm(String novaSenhaConfirm) {
        this.novaSenhaConfirm = novaSenhaConfirm;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
}
