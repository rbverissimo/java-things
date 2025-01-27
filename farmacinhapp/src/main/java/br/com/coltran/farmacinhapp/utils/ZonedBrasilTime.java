package br.com.coltran.farmacinhapp.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ZonedBrasilTime {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final ZoneId BR_ZONE_ID = ZoneId.of("America/Sao_Paulo");

    public ZonedDateTime dataHora(){
        return ZonedDateTime.now(BR_ZONE_ID);
    }

    public ZonedDateTime converterDate(String data){
        return LocalDate.parse(data, formatter).atStartOfDay(BR_ZONE_ID);
    }
}
