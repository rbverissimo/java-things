package br.com.coltran.farmacinhapp.controllers.web;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.domain.Remedio;
import br.com.coltran.farmacinhapp.repositories.MedidaRepository;
import br.com.coltran.farmacinhapp.repositories.TipoRemedioRepository;
import br.com.coltran.farmacinhapp.services.FarmaciaService;
import br.com.coltran.farmacinhapp.services.RemedioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/remedios")
public class RemediosController extends ControllerCommons {


    @Autowired
    private RemedioService remedioService;

    @Autowired
    private FarmaciaService farmaciaService;

    @Autowired
    private TipoRemedioRepository tipoRemedioRepository;

    @Autowired
    private MedidaRepository medidaRepository;

    @GetMapping("/i/{farmacia_id}")
    @PreAuthorize("@farmaciaService.isResourceOwner(#farmaciaId)")
    public String indexByFarmacia(@PathVariable("farmacia_id") long farmaciaId, Pageable pageable, Model model){
        remedioService.getRemediosByFarmacia(farmaciaId, pageable);
        model.addAttribute("farmaciaId", farmaciaId);
        return "remedios/index";
    }

    @GetMapping("/cadastro/{farmacia_id}")
    @PreAuthorize("@farmaciaService.isResourceOwner(#farmaciaId)")
    public String cadastroGET(@PathVariable("farmacia_id") long farmaciaId, @ModelAttribute Remedio remedio, Model model){
        model.addAttribute("farmaciaId", farmaciaId);
        model.addAttribute("tiposRemedio", tipoRemedioRepository.findAll());
        model.addAttribute("medidas", medidaRepository.findAll());
        return "remedios/cadastro";
    }

    @PostMapping("/cadastro/{farmacia_id}")
    @PreAuthorize("@farmaciaService.isResourceOwner(#farmaciaId)")
    public String cadastroPOST(@PathVariable("farmacia_id") long farmaciaId, @Valid @ModelAttribute Remedio remedio, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "/remedios/cadastro";

        Farmacia farmacia = farmaciaService.findResourceById(farmaciaId);
        if(farmacia != null) remedio.setFarmacia(farmacia);

        remedioService.save(remedio);
        return "redirect:/remedios/i/"+farmaciaId;
    }
}
