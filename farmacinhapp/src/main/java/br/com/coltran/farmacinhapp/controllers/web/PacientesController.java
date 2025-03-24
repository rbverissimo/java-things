package br.com.coltran.farmacinhapp.controllers.web;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.domain.Paciente;
import br.com.coltran.farmacinhapp.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PutMapping("/update/{id}")
    @PreAuthorize("@pacienteService.isResourceOwner(#pacienteId)")
    public String update(@PathVariable("id") long pacienteId, @Valid @ModelAttribute Paciente paciente, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) return "pacientes/index";
        Paciente managedPaciente = pacienteService.findResourceById(pacienteId);
        if(managedPaciente != null) pacienteService.update(managedPaciente,paciente);
        return "redirect:/pacientes/"+pacienteId;
    }


}
