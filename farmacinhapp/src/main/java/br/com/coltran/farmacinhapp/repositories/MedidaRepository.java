package br.com.coltran.farmacinhapp.repositories;

import br.com.coltran.farmacinhapp.domain.Medida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedidaRepository extends JpaRepository<Medida, Long> {
}
