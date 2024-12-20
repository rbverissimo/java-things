package br.com.coltran.farmacinhapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/remedios")
public class RemediosController extends ControllerCommons {


    @GetMapping("/i/{farmacia_id}")
    public String indexByFarmacia(@PathVariable("farmacia_id") int farmacia){
        return "";
    }
}
