package br.com.coltran.farmacinhapp.controllers;

import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.domain.Paciente;
import br.com.coltran.farmacinhapp.repositories.FarmaciaRepository;
import br.com.coltran.farmacinhapp.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/farmacias")
public class FarmaciasController extends ControllerCommons {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("farmacias", authService.usuarioLogado().getFarmacias());

        return "farmacias/index";
    }

    @PostMapping("/cadastro")
    public String cadastroPOST(@ModelAttribute Farmacia farmacia){

        Paciente paciente = farmacia.getPaciente();

        if(paciente != null){
           String inputNome =  paciente.getNome();
           paciente.setSobrenome(Arrays.stream(inputNome.split(" ")).skip(1).collect(Collectors.joining(" ")));
           setCriacaoAlteracaoAgora(paciente);
        }

        setCriacaoAlteracaoAgora(farmacia);

        farmaciaRepository.save(farmacia);
        return "redirect:/farmacias/";
    }

    @GetMapping("/cadastro")
    public String cadastroGET(@ModelAttribute Farmacia farmacia){
        if(!CollectionUtils.isEmpty(authService.usuarioLogado().getFarmacias()))  return "farmacias/index";
        return "farmacias/cadastro";
    }

}
