package br.com.coltran.farmacinhapp.repositories;

import br.com.coltran.farmacinhapp.domain.TipoRemedio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRemedioRepository extends JpaRepository<TipoRemedio, Long> {
}
