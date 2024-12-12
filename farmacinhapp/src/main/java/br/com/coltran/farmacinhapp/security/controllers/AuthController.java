package br.com.coltran.farmacinhapp.security.controllers;

import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.security.dto.UserRegDTO;
import br.com.coltran.farmacinhapp.security.repositories.UserRepository;
import br.com.coltran.farmacinhapp.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class AuthController {


    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        User user = new User();
        user.setEmail(userRegDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegDTO.getPassword()));
        user.setUsername(userRegDTO.getUsername());

        authService.cadastrarUsuario(user);

        return "redirect:/login";
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("usuario", authService.usuarioLogado());
        return "index";
    }
}
