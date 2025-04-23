package br.com.coltran.farmacinhapp.controllers.api.dto;

import java.io.Serializable;

public class ApiResponseDTO implements Serializable {

    private String mensagem;
    private String acoes;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getAcoes() {
        return acoes;
    }

    public void setAcoes(String acoes) {
        this.acoes = acoes;
    }

    public static class Builder {

        private final ApiResponseDTO dto;

        public Builder() {
            dto = new ApiResponseDTO();
        }

        public Builder mensagem(String mensagem){
            dto.setMensagem(mensagem);
            return this;
        }

        public Builder acoes(String acoes){
            dto.setAcoes(acoes);
            return this;
        }

        public ApiResponseDTO build(){
            return dto;
        }
    }
}
