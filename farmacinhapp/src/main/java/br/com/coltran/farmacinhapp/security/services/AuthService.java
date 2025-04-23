package br.com.coltran.farmacinhapp.security.services;

import br.com.coltran.farmacinhapp.controllers.api.dto.ChangePassword;
import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.security.domain.FarmaciaShareToken;
import br.com.coltran.farmacinhapp.security.exceptions.FarmaciaShareTokenException;
import br.com.coltran.farmacinhapp.security.exceptions.VerificationTokenNotFoundException;
import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.security.domain.VerificationToken;
import br.com.coltran.farmacinhapp.security.dto.UserRegDTO;
import br.com.coltran.farmacinhapp.security.repositories.FarmaciaShareTokenRepository;
import br.com.coltran.farmacinhapp.security.repositories.UserRepository;
import br.com.coltran.farmacinhapp.security.repositories.VerificationTokenRepository;
import br.com.coltran.farmacinhapp.utils.Colecoes;
import br.com.coltran.farmacinhapp.utils.ZonedBrasilTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private FarmaciaShareTokenRepository farmaciaShareTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ZonedBrasilTime zonedBrasilTime;

    @Autowired
    private Colecoes.SET<VerificationToken> colecoes;

    @Autowired
    private Colecoes.SET<Farmacia> colecoesFarmacia;

    private final String EMAIL_VERIFICATION_URL = "/verify";
    private final String FARMACIA_SHARE_URL = "/share-accepted";

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

        User user = new User();
        user.setEmail(userRegDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegDTO.getPassword()));
        user.setUsername(userRegDTO.getUsername());
        user.setDataCriacao(zonedBrasilTime.dataHora());
        user.setDataAlteracao(zonedBrasilTime.dataHora());
        user.setVerificado(false);

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.issueTo(user,verificationToken, zonedBrasilTime.dataHora(), EXPIRATION_LIMIT);

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

    public boolean isFarmaciaShareTokenValid(String token, long userId){
        User user = userRepository.findById(userId).orElse(null);
        return farmaciaShareTokenRepository.findByTokenAndUser(token, user).stream()
                .filter(farmaciaShareToken -> farmaciaShareToken.getExpiredAt().isAfter(zonedBrasilTime.dataHora()))
                .filter(farmaciaShareToken -> farmaciaShareToken.getVerifiedAt() == null)
                .anyMatch(farmaciaShareToken -> token.equals(farmaciaShareToken.getToken()));
    }

    public String generateVerificationUrl(User user) throws VerificationTokenNotFoundException{
         String token = user.getVerificationTokens().stream().max(Comparator.comparing(VerificationToken::getId))
                 .orElseThrow(() -> new VerificationTokenNotFoundException("Token de verificação de email não encontrado")).getToken();
        return String.format("%s?u=%d&verifier=%s", EMAIL_VERIFICATION_URL, user.getId(), token);
    }

    public String generateFarmaciaShareUrl(FarmaciaShareToken token){
        return String.format("%s?t=%s&u=%d", FARMACIA_SHARE_URL, token.getToken(), token.getUser().getId());
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

    @Transactional
    public FarmaciaShareToken updateVerifiedDateAndUpdateUserFarmacia(String token, long userId) throws FarmaciaShareTokenException {
        User user = userRepository.findById(userId).orElse(null);
        ZonedDateTime verifiedAt = zonedBrasilTime.dataHora();
        Optional<FarmaciaShareToken> farmaciaShareTokenOpt = farmaciaShareTokenRepository.findByTokenAndUser(token, user).stream()
                .filter(farmaciaShareToken -> farmaciaShareToken.getExpiredAt().isAfter(zonedBrasilTime.dataHora()))
                .filter(farmaciaShareToken -> farmaciaShareToken.getVerifiedAt() == null)
                .findFirst();

        farmaciaShareTokenOpt.ifPresentOrElse(t -> {
            t.setVerifiedAt(verifiedAt);
            farmaciaShareTokenRepository.save(t);

            Farmacia managedFarmaciaCompartilhada = t.getFarmacia();
            user.setFarmacias(colecoesFarmacia.addIfNull(user.getFarmacias(), managedFarmaciaCompartilhada));

            userRepository.save(user);

        }, () -> {
            throw  new FarmaciaShareTokenException("Token inválido.");
        });
        return farmaciaShareTokenOpt.get();
    }

    public FarmaciaShareToken registrarTokenFarmaciaCompartilhada(String userEmail, Farmacia farmacia) throws UsernameNotFoundException {
        User user = usuarioByEmail(userEmail).orElseThrow(() -> { throw new UsernameNotFoundException("Usuário não encontrado");});
        FarmaciaShareToken token = new FarmaciaShareToken();
        token.issueTo(user, token, zonedBrasilTime.dataHora(), EXPIRATION_LIMIT);
        token.setFarmacia(farmacia);
        return farmaciaShareTokenRepository.save(token);
    }

    public boolean isMatchingPassword(String raw, String encoded){
        return passwordEncoder.matches(raw, encoded);
    }
}
