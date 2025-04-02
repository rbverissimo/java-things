package br.com.coltran.farmacinhapp.utils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Component
public class Colecoes {

    @Component
    public static class SET<T> {

        public void adicionar(Set<T> set, T t){
            set.add(t);
        }

        public Set<T> addIfNull(Set<T> set, T t){
            if(CollectionUtils.isEmpty(set)) return new HashSet<T>(){{add(t);}};
            set.add(t);
            return set;
        }
    }
}
