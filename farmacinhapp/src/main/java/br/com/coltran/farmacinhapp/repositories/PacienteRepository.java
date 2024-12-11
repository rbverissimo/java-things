package br.com.coltran.farmacinhapp.repositories;

import br.com.coltran.farmacinhapp.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
