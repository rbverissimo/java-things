package br.com.coltran.farmacinhapp.domain.valueobjects.interfaces;

import br.com.coltran.farmacinhapp.domain.enums.MsgType;

public interface Mensagem {

    MsgType getMsgType();
    String getMensagem();
}
