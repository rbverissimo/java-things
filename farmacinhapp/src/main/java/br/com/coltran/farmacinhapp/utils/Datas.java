package br.com.coltran.farmacinhapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;

@Component
public class Datas {
    public int calcularIdade(LocalDate dtNascto){
        return Period.between(LocalDate.now(), dtNascto).getYears();
    }
}

