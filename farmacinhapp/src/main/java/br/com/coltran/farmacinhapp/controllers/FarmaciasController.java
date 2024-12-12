package br.com.coltran.farmacinhapp.controllers;

import br.com.coltran.farmacinhapp.repositories.FarmaciaRepository;
import br.com.coltran.farmacinhapp.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/farmacias")
public class FarmaciasController {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("farmacias", authService.usuarioLogado().getFarmacias());

        return "farmacias/index";
    }

    @GetMapping("/cadastro")
    public String cadastro(){
        if(!CollectionUtils.isEmpty(authService.usuarioLogado().getFarmacias()))  return "farmacias/index";
        return "farmacias/cadastro";
    }

}
