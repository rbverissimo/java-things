package br.com.coltran.farmacinhapp.controllers.web;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.services.GramaturaService;
import br.com.coltran.farmacinhapp.services.RemedioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gramaturas")
public class GramaturasController extends ControllerCommons {


    @Autowired
    private GramaturaService gramaturaService;

    @Autowired
    private RemedioService remedioService;

    @GetMapping("/cadastro/{remedio_id}")
    @PreAuthorize("@remedioService.isResourceOwner(#remedioId)")
    public String cadastroGET(@PathVariable("remedio_id") long remedioId){
        return "gramaturas/cadastro";
    }
}
