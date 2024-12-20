package br.com.coltran.farmacinhapp.repositories;

import br.com.coltran.farmacinhapp.domain.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemedioRepository extends JpaRepository<Remedio, Long> {
}
