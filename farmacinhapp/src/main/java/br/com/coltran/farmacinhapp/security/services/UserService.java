package br.com.coltran.farmacinhapp.security.services;

import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.security.dto.UserRegDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegDTO userRegDto);
}
