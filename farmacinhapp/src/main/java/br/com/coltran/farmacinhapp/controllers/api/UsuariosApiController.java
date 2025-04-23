package br.com.coltran.farmacinhapp.controllers.api;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.controllers.api.dto.ApiResponseDTO;
import br.com.coltran.farmacinhapp.controllers.api.dto.ChangePassword;
import br.com.coltran.farmacinhapp.security.domain.User;
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

        User managedUsuario = authService.usuarioLogado();

        if(!authService.isMatchingPassword(changePassword.getSenhaAtual(), managedUsuario.getPassword())) return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO.Builder().mensagem("A senha atual está incorreta").build());

        if(changePassword.getSenhaAtual().equals(changePassword.getNovaSenha())) return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO.Builder().mensagem("A nova senha não pode ser igual a senha atual").build());

        authService.encodeSenhaAlterada(managedUsuario, changePassword.getNovaSenha());
        authService.alterarUsuario(managedUsuario);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO.Builder().mensagem("A senha foi alterada com sucesso!").build());
    }
}
