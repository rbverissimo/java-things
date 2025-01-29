package br.com.coltran.farmacinhapp.config.converters;

import br.com.coltran.farmacinhapp.utils.ZonedBrasilTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {StringToZonedDateTimeConverter.class, ZonedBrasilTime.class})
public class StringToZonedDateTimeConverterTest {

    @Autowired
    private StringToZonedDateTimeConverter converter;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final ZoneId brZoneId = ZoneId.of("America/Sao_Paulo");

    @Test
    void testConverterDataValidaAssertDataCorreta(){
        String source = "2025-01-27";
        ZonedDateTime expected = LocalDate.parse(source, formatter).atStartOfDay(brZoneId);
        ZonedDateTime actual = converter.convert(source);
        assertEquals(expected, actual);
    }
}
