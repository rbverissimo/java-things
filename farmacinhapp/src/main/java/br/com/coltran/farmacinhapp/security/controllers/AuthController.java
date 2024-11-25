package br.com.coltran.farmacinhapp.security.controllers;

import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.security.dto.UserRegDTO;
import br.com.coltran.farmacinhapp.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRegDTO userRegDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()) return "register";
        User user = new User();
        user.setEmail(userRegDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegDTO.getPassword()));
        user.setUsername(userRegDTO.getUsername());

        userRepository.save(user);

        return  "redirect:/login";
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }
}
