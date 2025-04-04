package br.com.coltran.farmacinhapp.security.controllers;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.email.EmailServiceImpl;
import br.com.coltran.farmacinhapp.security.dto.UserRegDTO;
import br.com.coltran.farmacinhapp.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController extends ControllerCommons {


    @Autowired
    private AuthService authService;

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("userRegDto") UserRegDTO userRegDTO){
        return "register";
    }

    @PostMapping("/register")
    public String registerProcess(@Valid @ModelAttribute("userRegDto") UserRegDTO userRegDTO, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "register";
        }

        authService.salvar(userRegDTO);

        return "redirect:/login";
    }

}
