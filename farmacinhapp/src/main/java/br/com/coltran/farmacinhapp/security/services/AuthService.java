package br.com.coltran.farmacinhapp.security.services;

import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User usuarioLogado()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails)  authentication.getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername()).get();
    }

    public void salvarUsuario(User user){
        userRepository.save(user);
    }
}
