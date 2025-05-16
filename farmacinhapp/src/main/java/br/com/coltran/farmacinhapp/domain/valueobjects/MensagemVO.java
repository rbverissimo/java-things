package br.com.coltran.farmacinhapp.domain.valueobjects;

import br.com.coltran.farmacinhapp.domain.enums.MsgType;
import br.com.coltran.farmacinhapp.domain.valueobjects.interfaces.Mensagem;

public class MensagemVO implements Mensagem {

    private MsgType msgType;
    private String mensagem;

    public MensagemVO(MsgType msgType, String mensagem) {
        this.msgType = msgType;
        this.mensagem = mensagem;
    }

    @Override
    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    @Override
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
