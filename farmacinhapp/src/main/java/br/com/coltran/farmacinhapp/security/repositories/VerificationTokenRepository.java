package br.com.coltran.farmacinhapp.security.repositories;

import br.com.coltran.farmacinhapp.security.domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<List<VerificationToken>> findByUser(long user);

}
