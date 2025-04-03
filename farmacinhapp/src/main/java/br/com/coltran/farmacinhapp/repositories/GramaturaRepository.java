package br.com.coltran.farmacinhapp.repositories;

import br.com.coltran.farmacinhapp.domain.Gramatura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GramaturaRepository extends JpaRepository<Gramatura, Long> {

    @Query("SELECT g FROM Gramatura g JOIN g.remedios r WHERE r.id=:remedioId")
    Page<Gramatura> findByRemedioId(@Param("remedioId") Long remedioId, Pageable pageable);


}
