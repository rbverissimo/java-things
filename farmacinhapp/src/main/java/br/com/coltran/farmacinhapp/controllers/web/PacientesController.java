package br.com.coltran.farmacinhapp.controllers.web;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pacientes")
public class PacientesController extends ControllerCommons {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{id}")
    @PreAuthorize("@pacienteService.isResourceOwner(#pacienteId)")
    public String index(@PathVariable("id") long pacienteId, Model model){
        model.addAttribute("paciente", pacienteService.findResourceById(pacienteId));
        return "pacientes/index";
    }


}
