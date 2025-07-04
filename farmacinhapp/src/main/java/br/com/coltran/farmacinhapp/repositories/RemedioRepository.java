package br.com.coltran.farmacinhapp.repositories;

import br.com.coltran.farmacinhapp.domain.Remedio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface RemedioRepository extends JpaRepository<Remedio, Long> {

    @Query("SELECT r FROM Remedio r WHERE r.farmacia.id =:farmaciaId ")
    Page<Remedio> findAllByFarmacia(@Param("farmaciaId") Long farmaciaId, Pageable pageable);

    @Query("SELECT r FROM Remedio r JOIN r.farmacia WHERE (LOWER(r.nome) LIKE LOWER(CONCAT(:nome,'%'))) ORDER BY r.dataInicioTratamento DESC")
    Page<Remedio> findByNome(@Param("nome") String nome, Pageable pageable);

    @Query("SELECT r, g FROM Remedio r LEFT JOIN r.gramaturas g WHERE r.farmacia.id=:farmaciaId AND (LOWER(r.nome) LIKE LOWER(CONCAT(:nome, '%')))")
    Page<Remedio> findByFarmaciaAndNome(@Param("farmaciaId") Long farmaciaId, @Param("nome") String nome, Pageable pageable);

    @Query("SELECT r from Remedio r JOIN FETCH r.farmacia f JOIN f.users u WHERE u.id=:userId ORDER BY r.id DESC")
    Set<Remedio> findAllRemedioByUser(@Param("userId") Long userId);

    @Query("SELECT r from Remedio r JOIN r.farmacia f JOIN f.users u WHERE u.id=:userId ORDER BY r.id DESC")
    Page<Remedio> findAllRemedioPageByUser(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT DISTINCT r from Remedio r JOIN r.gramaturas g WHERE LOWER(g.principioAtivo) LIKE LOWER(CONCAT(:pa, '%'))")
    Page<Remedio> findRemedioByPrincipioAtivo(@Param("pa") String principioAtivo, Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Remedio r WHERE r.id=:id")
    void delete(@Param("id") Long id);
}
