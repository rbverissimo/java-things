package org.example;

import org.example.infra.FileContainer;

public class Main {
    public static void main(String[] args) {

        String myXml = FileContainer.getXmlAsString("src/main/resources/snoh-test-subject.xml");
        System.out.println(myXml);
    }
}