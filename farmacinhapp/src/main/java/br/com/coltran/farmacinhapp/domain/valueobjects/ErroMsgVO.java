package br.com.coltran.farmacinhapp.domain.valueobjects;

public class ErroMsgVO {

    private final String bsType = "danger";
    private final String mensagem;

    public ErroMsgVO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getBsType() {
        return bsType;
    }

    public String getMensagem() {
        return mensagem;
    }
}
