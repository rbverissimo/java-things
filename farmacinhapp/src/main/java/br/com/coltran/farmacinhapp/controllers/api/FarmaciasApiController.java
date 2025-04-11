package br.com.coltran.farmacinhapp.controllers.api;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.controllers.api.dto.ShareFarmacia;
import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.security.domain.FarmaciaShareToken;
import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.services.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/share")
    public ResponseEntity<?> compartilharFarmacia(@Valid @RequestBody ShareFarmacia shareFarmacia){

       if(shareFarmacia.getEmailUsuario().equals(authService.usuarioLogado().getEmail())) return ResponseEntity.badRequest()
               .body(Map.of("mensagem", "Você não pode compartilhar uma farmácia consigo mesmo(a)."));

       Optional<User> usuarioRecebedor = authService.usuarioByEmail(shareFarmacia.getEmailUsuario());

       if(!usuarioRecebedor.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
               .body(Map.of("mensagem", "O usuário declarado não foi encontrado"));


       Optional<Farmacia> farmaciaCompartilhada = farmaciaService.findByNomeAndUser(shareFarmacia.getNomeFarmacia());

       if(!farmaciaCompartilhada.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
               .body(Map.of("mensagem", "Sua farmácia não foi encontrada"));

       boolean isFarmaciaJaCompartilhadaComUsuario = usuarioRecebedor.get().getFarmacias().stream()
               .anyMatch(farmacia -> farmacia.getId() == farmaciaCompartilhada.get().getId());

       if(isFarmaciaJaCompartilhadaComUsuario) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
               .body(Map.of("mensagem", "A farmácia já foi compartilhada ou já é do usuário"));

       FarmaciaShareToken farmaciaShareToken = authService.registrarTokenFarmaciaCompartilhada(shareFarmacia.getEmailUsuario(), farmaciaCompartilhada.get());

       emailService.sendFarmaciaShare(usuarioRecebedor.get().getEmail(), usuarioRecebedor.get().getUsername(),
               authService.usuarioLogado().getUsername(), authService.generateFarmaciaShareUrl(farmaciaShareToken));

       return ResponseEntity.noContent().build();
    }
}
