package br.com.coltran.farmacinhapp.repositories;

import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.domain.Remedio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RemedioRepositoryTest {


    @Autowired
    private RemedioRepository remedioRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void findAllByFarmacia_retornaPageRemedios() {
        Farmacia farmacia1 = new Farmacia();
        farmacia1.setNome("Farmácia 1");
        entityManager.persistAndFlush(farmacia1);

        Remedio remedio1 = new Remedio();
        remedio1.setNome("Dorflex");
        remedio1.setFarmacia(farmacia1);
        entityManager.persistAndFlush(remedio1);

        Remedio remedio2 = new Remedio();
        remedio2.setNome("Ibuprofeno");
        remedio2.setFarmacia(farmacia1);
        entityManager.persistAndFlush(remedio2);

        Long farmaciaId = farmacia1.getId();
        Pageable pageable = PageRequest.of(0, 12);

        Page<Remedio> remedioPage = remedioRepository.findAllByFarmacia(farmaciaId, pageable);

        assertNotNull(remedioPage);
        assertEquals(2, remedioPage.getTotalElements());

    }



}
