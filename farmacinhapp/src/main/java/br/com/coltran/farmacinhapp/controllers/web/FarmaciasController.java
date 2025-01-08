package br.com.coltran.farmacinhapp.controllers.web;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.services.FarmaciaService;
import br.com.coltran.farmacinhapp.utils.Colecoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/farmacias")
public class FarmaciasController extends ControllerCommons {

    @Autowired
    private FarmaciaService farmaciaService;

    @Autowired
    private Colecoes.SET<Farmacia> colecoesSet;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("farmacias", authService.usuarioLogado().getFarmacias());
        return "farmacias/index";
    }

    @PostMapping("/cadastro")
    public String cadastroPOST(@Valid @ModelAttribute Farmacia farmacia, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()) return "/farmacias/cadastro";

        Optional.ofNullable(farmacia.getPaciente()).ifPresent(p -> {
            String inputNome =  p.getNome();
            p.setNome(inputNome.split(" ")[0]);
            p.setSobrenome(Arrays.stream(inputNome.split(" ")).skip(1).collect(Collectors.joining(" ")));
            p.setIdade(datasUtils.calcularIdade(p.getDataNascimento()));
        });

        farmaciaService.save(farmacia);

        User usuario = authService.usuarioLogado();
        colecoesSet.adicionar(usuario.getFarmacias(), farmacia);
        authService.alterarUsuario(usuario);

        return "redirect:/farmacias/";
    }

    @GetMapping("/cadastro")
    public String cadastroGET(@ModelAttribute Farmacia farmacia){
        if(!CollectionUtils.isEmpty(authService.usuarioLogado().getFarmacias()))  return "farmacias/index";
        return "farmacias/cadastro";
    }

}
