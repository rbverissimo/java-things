package br.com.coltran.farmacinhapp.controllers.web;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController extends ControllerCommons {

    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("usuario", authService.usuarioLogado());
        return "index";
    }
}
