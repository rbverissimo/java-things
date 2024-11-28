package br.com.coltran.farmacinhapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("/error")
    public String error(){
        return "error.html";
    }

    @GetMapping("/page")
    public String page(){
        return "page";
    }
}
