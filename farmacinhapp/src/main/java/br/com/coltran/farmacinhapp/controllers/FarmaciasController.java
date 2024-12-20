package br.com.coltran.farmacinhapp.controllers;

import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.domain.Paciente;
import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;
import br.com.coltran.farmacinhapp.repositories.FarmaciaRepository;
import br.com.coltran.farmacinhapp.security.domain.User;
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
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/farmacias")
public class FarmaciasController extends ControllerCommons {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

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

        Paciente paciente = farmacia.getPaciente();

        if(paciente != null){
           String inputNome =  paciente.getNome();
           paciente.setSobrenome(Arrays.stream(inputNome.split(" ")).skip(1).collect(Collectors.joining(" ")));
           setCriacaoAlteracaoAgora(paciente);
        }
        setCriacaoAlteracaoAgora(farmacia);
        farmaciaRepository.save(farmacia);

        User usuario = authService.usuarioLogado();
        colecoesSet.adicionar(usuario.getFarmacias(), farmacia);
        authService.salvarUsuario(usuario);

        return "redirect:/farmacias/";
    }

    @GetMapping("/cadastro")
    public String cadastroGET(@ModelAttribute Farmacia farmacia){
        if(!CollectionUtils.isEmpty(authService.usuarioLogado().getFarmacias()))  return "farmacias/index";
        return "farmacias/cadastro";
    }

}
