package org.example.infra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileContainer {

    public static String getXmlAsString(String path){
        String xmlString = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine()) != null){
                xmlString += line;
            }
        } catch(IOException e){
            e.printStackTrace(System.out);
        }
        return xmlString;
    }
}
