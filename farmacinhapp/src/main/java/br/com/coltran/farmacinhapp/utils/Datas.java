package br.com.coltran.farmacinhapp.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class Datas {

    public int calcularIdade(LocalDate dtNascto){
        return Period.between(LocalDate.now(), dtNascto).getYears();
    }
}

