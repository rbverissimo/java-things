package br.com.coltran.farmacinhapp.domain.valueobjects;

import br.com.coltran.farmacinhapp.domain.enums.MsgType;

public class ErroMsgVO extends MensagemVO {

    public ErroMsgVO(String mensagem) {
        super(MsgType.ERROR, mensagem);
    }
}
