package br.com.coltran.farmacinhapp.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {


    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }
}
