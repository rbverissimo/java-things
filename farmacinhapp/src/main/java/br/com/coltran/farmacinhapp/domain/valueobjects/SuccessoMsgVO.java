package br.com.coltran.farmacinhapp.domain.valueobjects;

import br.com.coltran.farmacinhapp.domain.enums.MsgType;

public class SuccessoMsgVO extends MensagemVO {
    public SuccessoMsgVO(String mensagem) {
        super(MsgType.SUCCESS, mensagem);
    }
}
