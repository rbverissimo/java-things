package br.com.coltran.farmacinhapp.utils;

import br.com.coltran.farmacinhapp.domain.adapters.BootstrapMessage;
import br.com.coltran.farmacinhapp.domain.interfaces.UIMessage;
import br.com.coltran.farmacinhapp.domain.valueobjects.MensagemVO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageProcessor {

    private List<UIMessage> mensagens;

    public MessageProcessor() {
    }

    public List<UIMessage> process(UIMessage... messages){
        return Arrays.stream(messages).collect(Collectors.toList());
    }

    public List<UIMessage> process(MensagemVO... mensagens){
        return Arrays.stream(mensagens).map(BootstrapMessage::new).collect(Collectors.toList());
    }


}
