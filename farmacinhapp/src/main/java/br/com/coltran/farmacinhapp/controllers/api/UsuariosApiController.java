package br.com.coltran.farmacinhapp.controllers.api;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.controllers.api.dto.ApiResponseDTO;
import br.com.coltran.farmacinhapp.controllers.api.dto.ChangePassword;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/usuarios")
public class UsuariosApiController extends ControllerCommons {


    @PutMapping("/change-password")
    public ResponseEntity<?> alterarSenha(@Valid @RequestBody ChangePassword changePassword){

        if(!changePassword.getSenhaAtual().equals(authService.usuarioLogado().getPassword())) return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO.Builder().mensagem("A senha atual está incorreta").build());

        return null;
    }
}
