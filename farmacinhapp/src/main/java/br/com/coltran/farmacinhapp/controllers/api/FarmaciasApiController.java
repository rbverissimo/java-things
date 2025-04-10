package br.com.coltran.farmacinhapp.controllers.api;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.controllers.api.dto.ShareFarmacia;
import br.com.coltran.farmacinhapp.exceptions.FarmaciaException;
import br.com.coltran.farmacinhapp.services.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/farmacias")
public class FarmaciasApiController extends ControllerCommons {

    @Autowired
    private FarmaciaService farmaciaService;

    @PostMapping("/share")
    public ResponseEntity<?> compartilharFarmacia(@Valid @RequestBody ShareFarmacia shareFarmacia){

       if(shareFarmacia.getEmailUsuario().equals(authService.usuarioLogado().getEmail())) return ResponseEntity.badRequest()
               .body(Map.of("mensagem", "Você não pode compartilhar uma farmácia consigo mesmo(a)."));

       if(!authService.usuarioByEmail(shareFarmacia.getEmailUsuario()).isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
               .body(Map.of("mensagem", "O usuário declarado não foi encontrado"));

       try{
           farmaciaService.compartilharComUsuario(shareFarmacia.getNomeFarmacia(), shareFarmacia.getEmailUsuario());
       } catch (FarmaciaException | UsernameNotFoundException e) {
           e.printStackTrace(System.err);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", e.getMessage()));
       }

       return ResponseEntity.noContent().build();
    }
}
