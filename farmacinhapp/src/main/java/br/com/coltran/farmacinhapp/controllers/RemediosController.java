package br.com.coltran.farmacinhapp.controllers;

import br.com.coltran.farmacinhapp.domain.Remedio;
import br.com.coltran.farmacinhapp.services.FarmaciaService;
import br.com.coltran.farmacinhapp.services.RemedioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/remedios")
public class RemediosController extends ControllerCommons {


    @Autowired
    private RemedioService remedioService;

    @Autowired
    private FarmaciaService farmaciaService;

    @GetMapping("/i/{farmacia_id}")
    @PreAuthorize("@farmaciaService.isResourceOwner(#farmaciaId)")
    public String indexByFarmacia(@PathVariable("farmacia_id") long farmaciaId, Pageable pageable, Model model){
        remedioService.getRemediosByFarmacia(farmaciaId, pageable);
        model.addAttribute("farmaciaId", farmaciaId);
        return "remedios/index";
    }

    @GetMapping("/cadastro/{farmacia_id}")
    @PreAuthorize("@farmaciaService.isResourceOwner(#farmaciaId)")
    public String cadastroGET(@PathVariable("farmacia_id") long farmaciaId, Model model){
        model.addAttribute("farmaciaId", farmaciaId);
        return "remedios/cadastro";
    }

    @PostMapping("/cadastro/{farmacia_id}")
    @PreAuthorize("@farmaciaService.isResourceOwner(#farmacia_id)")
    public String cadastroPOST(@PathVariable("farmacia_id") long farmaciaId, @Valid @ModelAttribute Remedio remedio){

        return "";
    }
}
