package br.com.coltran.farmacinhapp.repositories;

import br.com.coltran.farmacinhapp.domain.Gramatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GramaturaRepository extends JpaRepository<Gramatura, Long> {


}
