package org.example.contexts.retornoEvento;

import org.apache.commons.lang3.StringUtils;
import org.example.infra.FileContainer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RetornoEventoContext {

    private String XML_EVENTO;

    public RetornoEventoContext(String path) {
        XML_EVENTO = FileContainer.getXmlAsString(path);
    }

    public RetornoEventoContext(){

    }

    public String getIdFromXmlESocial(String xml){
        this.XML_EVENTO = xml;
        return getIdFromXmlESocial();
    }

    public String getIdFromXmlESocial(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new InputSource(new java.io.StringReader(XML_EVENTO)));
            document.getDocumentElement().normalize();

            Element rootElement = document.getDocumentElement();

            if (rootElement.getNodeName().equals("eSocial")) {
                Element retornoEvento = (Element) rootElement.getElementsByTagName("retornoEvento").item(0);
                return retornoEvento.getAttribute("Id");
            } else {
                throw new RuntimeException("Invalid root element. Expected 'eSocial'");
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<String> getXmlEventos(){
        return Arrays.stream(Paths.values())
                .map(enumerator -> FileContainer.getXmlAsString(enumerator.getPath()))
                .collect(Collectors.toList());
    }
}
