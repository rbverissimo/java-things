package br.com.coltran.farmacinhapp.repositories;

import br.com.coltran.farmacinhapp.domain.Remedio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RemedioRepository extends JpaRepository<Remedio, Long> {

    @Query("SELECT r FROM Remedio r WHERE r.farmacia.id =:farmaciaId ")
    public Page<Remedio> findAllByFarmacia(@Param("farmaciaId") Long farmaciaId, Pageable pageable);
}
