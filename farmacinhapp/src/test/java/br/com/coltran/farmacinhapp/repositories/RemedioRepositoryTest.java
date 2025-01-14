package br.com.coltran.farmacinhapp.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


@DataJpaTest
public class RemedioRepositoryTest {


    @Autowired
    private RemedioRepository remedioRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void findAllByFarmacia_retornaPageRemedios() {

    }



}
