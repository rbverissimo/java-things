package org.example;

import org.example.abstractions.IESocialTotalizador;
import org.example.factory.ESocialTotalizadorFactory;
import org.example.infrastructure.FileContainer;


public class Main {
    public static void main(String[] args) {

        FileContainer fileContainer = new FileContainer();
        String xml = fileContainer.getXmlAsString("src/main/resources/totalizador-INSS-teste.xml");
        IESocialTotalizador eSocialTotalizador = ESocialTotalizadorFactory.create(xml);

        System.out.println(eSocialTotalizador.getNomeTotalizador());


    }
}