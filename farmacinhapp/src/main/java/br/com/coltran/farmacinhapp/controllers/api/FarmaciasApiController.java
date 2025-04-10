package br.com.coltran.farmacinhapp.controllers.api;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.controllers.api.dto.ShareFarmacia;
import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.services.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/farmacias")
public class FarmaciasApiController extends ControllerCommons {

    @Autowired
    private FarmaciaService farmaciaService;

    @Value("${app.share.farmacia.secret:4090}")
    private long APP_SHARE_FARMACIA_SECRET;

    private String SHARE_FARMACIA_ACCEPT = "/farmacias?s=";

    @PostMapping("/share")
    public ResponseEntity<?> compartilharFarmacia(@Valid @RequestBody ShareFarmacia shareFarmacia){

       if(shareFarmacia.getEmailUsuario().equals(authService.usuarioLogado().getEmail())) return ResponseEntity.badRequest()
               .body(Map.of("mensagem", "Você não pode compartilhar uma farmácia consigo mesmo(a)."));

       Optional<User> usuarioRecebedor = authService.usuarioByEmail(shareFarmacia.getEmailUsuario());

       if(!usuarioRecebedor.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
               .body(Map.of("mensagem", "O usuário declarado não foi encontrado"));

       emailService.sendFarmaciaShare(shareFarmacia.getEmailUsuario(),
               authService.usuarioLogado().getEmail(),
               authService.usuarioLogado().getUsername(),
               SHARE_FARMACIA_ACCEPT+APP_SHARE_FARMACIA_SECRET+"&f="+shareFarmacia.getNomeFarmacia());


       return ResponseEntity.noContent().build();
    }
}
