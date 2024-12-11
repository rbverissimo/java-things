package br.com.coltran.farmacinhapp.repositories;

import br.com.coltran.farmacinhapp.domain.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
}
