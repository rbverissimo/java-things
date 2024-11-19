package br.com.coltran.farmacinhapp.security.repositories;

import br.com.coltran.farmacinhapp.security.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
