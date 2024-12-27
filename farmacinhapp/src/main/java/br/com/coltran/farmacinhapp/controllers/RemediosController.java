package br.com.coltran.farmacinhapp.controllers;

import br.com.coltran.farmacinhapp.services.FarmaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/remedios")
public class RemediosController extends ControllerCommons {


    @Autowired
    private FarmaciaService farmaciaService;

    @GetMapping("/i/{farmacia_id}")
    @PreAuthorize("@farmaciaService.isResourceOwner(#farmaciaId)")
    public String indexByFarmacia(@PathVariable("farmacia_id") int farmaciaId, Model model){
        model.addAttribute("farmacia", farmaciaService.findResourceById(farmaciaId));
        return "remedios/index";
    }

    public String cadastroPOST(){
        return "";
    }
}
