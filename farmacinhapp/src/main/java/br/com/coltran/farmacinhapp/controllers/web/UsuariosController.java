package br.com.coltran.farmacinhapp.controllers.web;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.controllers.web.dto.UpdateUserDto;
import br.com.coltran.farmacinhapp.security.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuariosController extends ControllerCommons {

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("updateUserDto", authService.usuarioLogado());
        return "usuarios/index";
    }

    @PutMapping("/update")
    public String update(@Valid @ModelAttribute UpdateUserDto updateUserDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("updateUserDto", updateUserDto);
            return "usuarios/index";
        }

        authService.alterarDadosUsuario(updateUserDto);
        return "redirect:/usuario/";
    }
}
