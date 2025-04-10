package br.com.coltran.farmacinhapp.security.repositories;

import br.com.coltran.farmacinhapp.security.domain.FarmaciaShareToken;
import br.com.coltran.farmacinhapp.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmaciaShareTokenRepository extends JpaRepository<FarmaciaShareToken, Long> {

    Optional<FarmaciaShareToken> findByTokenAndUser(String token, User user);
}
