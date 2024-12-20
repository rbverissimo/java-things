package br.com.coltran.farmacinhapp.utils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Colecoes {

    @Component
    public static class SET<T> {

        public void adicionar(Set<T> set, T t){
            set.add(t);
        }
    }
}
