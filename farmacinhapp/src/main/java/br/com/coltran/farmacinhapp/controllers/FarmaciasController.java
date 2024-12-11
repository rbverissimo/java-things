package br.com.coltran.farmacinhapp.controllers;

import br.com.coltran.farmacinhapp.repositories.FarmaciaRepository;
import br.com.coltran.farmacinhapp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        authService.usuarioLogado().ifPresent(u -> {
            model.addAttribute("farmacias", u.getFarmacias());
        });

        return "farmacias";
    }

}
