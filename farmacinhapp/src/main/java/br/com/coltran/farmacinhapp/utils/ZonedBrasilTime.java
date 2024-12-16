package br.com.coltran.farmacinhapp.utils;

import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class ZonedBrasilTime {

    public ZonedDateTime dataHora(){
        ZoneId brZoneId = ZoneId.of("America/Sao_Paulo");
        return ZonedDateTime.now(brZoneId);
    }
}
