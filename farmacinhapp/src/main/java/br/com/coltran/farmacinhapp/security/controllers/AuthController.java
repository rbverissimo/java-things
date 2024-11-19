package br.com.coltran.farmacinhapp.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {


    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @PostMapping("/auth_login")
    public String authLogin(){
        return "";
    }
}
