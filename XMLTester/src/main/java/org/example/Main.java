package org.example;

import org.example.abstractions.IESocialTotalizador;
import org.example.factory.ESocialTotalizadorFactory;
import org.example.infrastructure.FileContainer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        //File xmlFile = new File("src/main/resources/totalizador-INSS-teste.xml");
        //"src/main/resources/totalizador-INSS-teste.xml"

        FileContainer fileContainer = new FileContainer();
        String xml = fileContainer.getXmlAsString("src/main/resources/totalizador-INSS-teste.xml");
        IESocialTotalizador eSocialTotalizador = ESocialTotalizadorFactory.create(xml);

        System.out.println(eSocialTotalizador.getNomeTotalizador());


    }
}