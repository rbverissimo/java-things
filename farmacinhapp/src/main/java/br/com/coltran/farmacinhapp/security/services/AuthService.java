package br.com.coltran.farmacinhapp.security.services;

import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.security.dto.UserRegDTO;
import br.com.coltran.farmacinhapp.security.repositories.UserRepository;
import br.com.coltran.farmacinhapp.utils.ZonedBrasilTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ZonedBrasilTime zonedBrasilTime;

    public User usuarioLogado()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails)  authentication.getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername()).get();
    }

    public Optional<User> usuarioByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User salvar(UserRegDTO userRegDTO){
        User user = new User();
        user.setEmail(userRegDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegDTO.getPassword()));
        user.setUsername(userRegDTO.getUsername());
        user.setDataCriacao(zonedBrasilTime.dataHora());
        user.setDataAlteracao(zonedBrasilTime.dataHora());
        return userRepository.save(user);
    }

    public void alterarUsuario(User user) {
        user.setDataAlteracao(zonedBrasilTime.dataHora());
        userRepository.save(user);
    }

    public Optional<User> usuarioVerificadoByEmail(String email){
        return usuarioByEmail(email).filter(this::isUsuarioVerificado);
    }

    public boolean isUsuarioVerificado(User user){
        return user.getVerificationTokens()
                .stream()
                .anyMatch(verificationToken -> verificationToken.getVerifiedAt() != null
                        && verificationToken.getVerifiedAt().isBefore(zonedBrasilTime.dataHora()));
    }

}
