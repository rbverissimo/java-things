package br.com.coltran.farmacinhapp.config.converters;

import br.com.coltran.farmacinhapp.utils.ZonedBrasilTime;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class StringToZonedDateTimeConverter implements Converter<String, ZonedDateTime> {

    @Autowired
    private ZonedBrasilTime zonedBrasilTime;

    @Override
    public ZonedDateTime convert(String source) {
        if(StringUtils.isBlank(source)) return  null;
        return zonedBrasilTime.converterDate(source);
    }
}
