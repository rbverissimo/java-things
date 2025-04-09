package br.com.coltran.farmacinhapp.security.services;

import br.com.coltran.farmacinhapp.security.exceptions.VerificationTokenNotFoundException;
import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.security.domain.VerificationToken;
import br.com.coltran.farmacinhapp.security.dto.UserRegDTO;
import br.com.coltran.farmacinhapp.security.repositories.UserRepository;
import br.com.coltran.farmacinhapp.security.repositories.VerificationTokenRepository;
import br.com.coltran.farmacinhapp.utils.Colecoes;
import br.com.coltran.farmacinhapp.utils.ZonedBrasilTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;

@Component
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ZonedBrasilTime zonedBrasilTime;

    @Autowired
    private Colecoes.SET<VerificationToken> colecoes;

    private final String EMAIL_VERIFICATION_URL = "/verify";
    private final Integer EXPIRATION_LIMIT = 24;

    public User usuarioLogado()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails)  authentication.getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername()).get();
    }

    public Optional<User> usuarioByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> usuarioByEmailNaoVerificado(String email){
        return userRepository.findByEmailAndVerificadoFalse(email);
    }

    @Transactional
    public User salvar(UserRegDTO userRegDTO){

        UUID token = UUID.randomUUID();

        User user = new User();
        user.setEmail(userRegDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegDTO.getPassword()));
        user.setUsername(userRegDTO.getUsername());
        user.setDataCriacao(zonedBrasilTime.dataHora());
        user.setDataAlteracao(zonedBrasilTime.dataHora());
        user.setVerificado(false);

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token.toString());
        verificationToken.setUser(user);
        verificationToken.setExpiredAt(zonedBrasilTime.dataHora().plusHours(EXPIRATION_LIMIT));

        user.setVerificationTokens(colecoes.addIfNull(user.getVerificationTokens(), verificationToken));

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

    public boolean isVerificationTokenValido(long userId, String token){
        return userRepository.findById(userId)
                .stream()
                .map(User::getVerificationTokens)
                .anyMatch(verificationTokens -> verificationTokens.stream()
                        .filter(verificationToken -> verificationToken.getExpiredAt().isAfter(zonedBrasilTime.dataHora()))
                        .anyMatch(verificationToken -> token.equals(verificationToken.getToken())));
    }

    public String generateVerificationUrl(User user) throws VerificationTokenNotFoundException{
         String token = user.getVerificationTokens().stream().max(Comparator.comparing(VerificationToken::getId))
                 .orElseThrow(() -> new VerificationTokenNotFoundException("Token de verificação de email não encontrado")).getToken();
        return String.format("%s?u=%d&verifier=%s", EMAIL_VERIFICATION_URL, user.getId(), token);
    }

    @Transactional
    public VerificationToken updateVerifiedDate(long userId, String uuidToken) throws VerificationTokenNotFoundException{

        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByUserAndToken(userId, uuidToken);
        ZonedDateTime verifiedAt = zonedBrasilTime.dataHora();
        verificationToken.ifPresent(v -> {
            v.setVerifiedAt(verifiedAt);
            verificationTokenRepository.save(v);

            User managedUser = v.getUser();
            managedUser.setVerificado(true);
            userRepository.save(managedUser);

        });
        return verificationToken.orElseThrow(() -> new VerificationTokenNotFoundException("Token de verificação de email não encontrado"));
    }

}
