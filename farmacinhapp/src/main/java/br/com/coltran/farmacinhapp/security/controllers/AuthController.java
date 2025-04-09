package br.com.coltran.farmacinhapp.security.controllers;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.email.EmailServiceImpl;
import br.com.coltran.farmacinhapp.security.exceptions.VerificationTokenNotFoundException;
import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.security.domain.VerificationToken;
import br.com.coltran.farmacinhapp.security.dto.UserRegDTO;
import br.com.coltran.farmacinhapp.security.services.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AuthController extends ControllerCommons {


    @Autowired
    private AuthService authService;

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping("/login")
    public String login(Model model){ return "login"; }

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

        if(authService.usuarioByEmail(userRegDTO.getEmail()).isPresent()){
            model.addAttribute("emailRegistrado", userRegDTO.getEmail());
            model.addAttribute("infoMsg", "Já existe um usuário associado a este e-mail!");
            return "login";
        }

        User registeredUser = authService.salvar(userRegDTO);

        try{
            emailService.sendEmailVerification(registeredUser.getEmail(), registeredUser.getUsername(), authService.generateVerificationUrl(registeredUser));
            model.addAttribute("infoMsg", "Um e-mail de confirmação foi enviado ao seu e-mail. Confirme-o para acessar a aplicação.");

        } catch (VerificationTokenNotFoundException e){
            e.printStackTrace(System.err);
            model.addAttribute("infoErr", "Um erro foi encontrado ao realizar o seu registro. Tente novamente mais tarde");
        }

        return "login";
    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam(name = "u", required = true) long userId, @RequestParam(name = "verifier", required = true) String uuidToken, Model model){
        try{
            boolean isTokenValido = authService.isVerificationTokenValido(userId, uuidToken);

            if(isTokenValido) {
                VerificationToken verificationToken = authService.updateVerifiedDate(userId, uuidToken);
                model.addAttribute("infoSucc", "O seu e-mail foi validado com sucesso!");
                model.addAttribute("emailRegistrado", verificationToken.getUser().getEmail());
            }
        } catch(VerificationTokenNotFoundException e){
            e.printStackTrace(System.err);
            model.addAttribute("infoErr", "Um erro foi encontrado.");
        }
        return "login";
    }

    @GetMapping("/resend")
    public String resendVerificationEmail(@RequestParam(name = "email", required = false) String email, Model model){

        if(StringUtils.isBlank(email)) return "resend-verification";

        authService.usuarioByEmailNaoVerificado(email).ifPresentOrElse(u -> {

                try{
                    emailService.sendEmailVerification(u.getEmail(), u.getUsername(), authService.generateVerificationUrl(u));
                    model.addAttribute("infoMsg", "O e-mail de confirmação foi reenviado");

                } catch (VerificationTokenNotFoundException e){
                    e.printStackTrace(System.err);
                    model.addAttribute("infoErr", "Não foi possível re-enviar o e-mail de confirmação");
                }},
                () -> { model.addAttribute("infoErr", "Usuário não cadastrado");
        });


        return "login";
    }

}
