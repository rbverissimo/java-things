package br.com.coltran.farmacinhapp.controllers.web;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuariosController extends ControllerCommons {

    @GetMapping("/")
    public String index(){
        return "usuarios/index";
    }
}
