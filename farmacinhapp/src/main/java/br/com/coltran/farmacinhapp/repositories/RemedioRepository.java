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

    @Query("SELECT r, g FROM Remedio r JOIN r.gramaturas g WHERE r.farmacia.id=:farmaciaId AND (LOWER(r.nome) LIKE LOWER(CONCAT(:nome, '%')))")
    public Page<Remedio> findByNome(@Param("farmaciaId") Long farmaciaId, @Param("nome") String nome, Pageable pageable);
}
