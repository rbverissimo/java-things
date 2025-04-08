package br.com.coltran.farmacinhapp.security.repositories;

import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.security.domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<List<VerificationToken>> findByUser(long user);
    Optional<VerificationToken> findFirstByUserOrderByIdDesc(User user);

    @Query("SELECT vt FROM VerificationToken vt WHERE vt.user.id = :userId AND vt.token = :token")
    Optional<VerificationToken> findByUserAndToken(@Param("userId") long userId, @Param("token") String token);

}
