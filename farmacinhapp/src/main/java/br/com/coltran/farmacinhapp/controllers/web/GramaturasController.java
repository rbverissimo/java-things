package br.com.coltran.farmacinhapp.controllers.web;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.domain.Gramatura;
import br.com.coltran.farmacinhapp.domain.Remedio;
import br.com.coltran.farmacinhapp.domain.adapters.BootstrapMessage;
import br.com.coltran.farmacinhapp.domain.interfaces.UIMessage;
import br.com.coltran.farmacinhapp.domain.valueobjects.SuccessoMsgVO;
import br.com.coltran.farmacinhapp.repositories.MedidaRepository;
import br.com.coltran.farmacinhapp.services.GramaturaService;
import br.com.coltran.farmacinhapp.services.RemedioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/gramaturas")
public class GramaturasController extends ControllerCommons {


    @Autowired
    private GramaturaService gramaturaService;

    @Autowired
    private RemedioService remedioService;

    @Autowired
    private MedidaRepository medidaRepository;

    @GetMapping("/cadastro")
    public String cadastroGET(@ModelAttribute Gramatura gramatura, Model model){
        model.addAttribute("medidas", medidaRepository.findAll());
        return "gramaturas/cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastroPOST(@Valid @ModelAttribute Gramatura gramatura, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        List<UIMessage> mensagens = new ArrayList<>();
        if(bindingResult.hasErrors()){
            model.addAttribute("medidas", medidaRepository.findAll());
            return "gramaturas/cadastro";
        }
        redirectAttributes.addFlashAttribute(mensagens);
        gramaturaService.save(gramatura);
        mensagens.add(new BootstrapMessage(new SuccessoMsgVO("Gramatura salva com sucesso!")));

        return "redirect:/gramaturas/show/"+gramatura.getId();
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("@gramaturaService.isResourceOwner(#gramaturaId)")
    public String show(@PathVariable("id") long gramaturaId, Model model){
        model.addAttribute("gramatura", gramaturaService.findResourceById(gramaturaId));
        model.addAttribute("medidas", medidaRepository.findAll());
        return "gramaturas/cadastro";
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("@gramaturaSerivce.isResourceOwner(#gramaturaId)")
    public String edit(@PathVariable("id") long gramaturaId, @Valid @ModelAttribute Gramatura gramatura, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            model.addAttribute("medidas", medidaRepository.findAll());
            return "gramaturas/show/"+gramaturaId;
        }

        gramaturaService.update(gramaturaService.findResourceById(gramaturaId), gramatura);

        List<UIMessage> mensagens = messageProcessor.process(new BootstrapMessage(new SuccessoMsgVO("A gramatura foi atualizada com sucesso!")));
        redirectAttributes.addFlashAttribute(mensagens);
        return "redirect:/gramaturas/show/"+gramaturaId;
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("@gramaturaService.isResourceOwner(#gramaturaId)")
    public String destroy(@PathVariable("id") long gramaturaId){
        return "";
    }
}
