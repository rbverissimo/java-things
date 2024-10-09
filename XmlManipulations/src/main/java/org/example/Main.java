package org.example;

import org.example.contexts.retornoEvento.RetornoEventoContext;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> xmlEventos = RetornoEventoContext.getXmlEventos();

        Map<String, String> xmlEventosMap = xmlEventos.stream().collect(Collectors.toMap(
                xml -> new RetornoEventoContext().getIdFromXmlESocial(xml),
                xml -> xml
        ));


        xmlEventosMap.keySet().stream().forEach(k -> {
            System.out.println(k);
            System.out.println(xmlEventosMap.get(k));
        });


    }


}