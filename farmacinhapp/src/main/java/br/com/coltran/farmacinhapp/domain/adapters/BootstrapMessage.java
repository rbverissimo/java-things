package br.com.coltran.farmacinhapp.domain.adapters;

import br.com.coltran.farmacinhapp.domain.enums.MsgType;
import br.com.coltran.farmacinhapp.domain.interfaces.UIMessage;
import br.com.coltran.farmacinhapp.domain.valueobjects.interfaces.Mensagem;

public class BootstrapMessage implements UIMessage {

    private final String type;
    private final String mensagem;

    public BootstrapMessage(Mensagem mensagem) {
        type = adaptType(mensagem.getMsgType());
        this.mensagem = mensagem.getMensagem();
    }

    private String adaptType(MsgType msgType){
        switch (msgType){
            case SUCCESS:
                return "success";
            case ERROR:
                return "danger";
            case ALERT:
                return "warning";
            default:
                return "info";
        }
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getMensagem() {
        return mensagem;
    }
}
