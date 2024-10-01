package org.example.factory;

import org.example.abstractions.IESocialTotalizador;
import org.example.model.ESocialTotalizadorINSS;
import org.example.model.ESocialTotalizadorINSSPA;
import org.example.model.ESocialTotalizadorIRRF;

import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ESocialTotalizadorFactory {

    private static final Pattern tipoPtrn = Pattern.compile("tipo=\"(\\w+)\"");
    private static final Map<String, Supplier<IESocialTotalizador>> totMap = Map.of(
            "S5001", ESocialTotalizadorINSS::new,
            "S5002", ESocialTotalizadorIRRF::new,
            "S5011", ESocialTotalizadorINSSPA::new
    );

    public static IESocialTotalizador create(String xml){
        Matcher matcher = tipoPtrn.matcher(xml);
        if(matcher.find()){
            String tipo = matcher.group(1);
            return totMap.getOrDefault(tipo, () -> null).get();
        } else {
            return null;
        }
    }
}
