package br.com.coltran.farmacinhapp.controllers.web;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.domain.adapters.BootstrapMessage;
import br.com.coltran.farmacinhapp.domain.interfaces.UIMessage;
import br.com.coltran.farmacinhapp.domain.valueobjects.ErroMsgVO;
import br.com.coltran.farmacinhapp.domain.valueobjects.SuccessoMsgVO;
import br.com.coltran.farmacinhapp.domain.valueobjects.interfaces.Mensagem;
import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.services.FarmaciaService;
import br.com.coltran.farmacinhapp.utils.Colecoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;
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
    public String cadastroPOST(@Valid @ModelAttribute Farmacia farmacia, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        List<UIMessage> mensagens = new ArrayList<>();

        if(bindingResult.hasErrors()) return "/farmacias/cadastro";

        User usuario = authService.usuarioLogado();
        if(usuario.getFarmacias().size() > 1){
            mensagens.add(new BootstrapMessage(new ErroMsgVO("O seu limite de farmácias foi atingido.")));
            model.addAttribute("mensagens", mensagens);
            return "/farmacias/cadastro";
        }

        Optional.ofNullable(farmacia.getPaciente()).ifPresent(p -> {
            String inputNome =  p.getNome();
            p.setNome(inputNome.split(" ")[0]);
            p.setSobrenome(Arrays.stream(inputNome.split(" ")).skip(1).collect(Collectors.joining(" ")));
            p.setIdade(datasUtils.calcularIdade(p.getDataNascimento()));
        });

        farmaciaService.save(farmacia);

        colecoesSet.adicionar(usuario.getFarmacias(), farmacia);
        authService.alterarUsuario(usuario);

        mensagens.add(new BootstrapMessage(new SuccessoMsgVO("A farmácia foi criada com sucesso.")));

        redirectAttributes.addFlashAttribute(mensagens);

        return "redirect:/farmacias/";
    }

    @GetMapping("/cadastro")
    public String cadastroGET(@ModelAttribute Farmacia farmacia){
        Set<Farmacia> farmaciasUsuario = authService.usuarioLogado().getFarmacias();
        return "farmacias/cadastro";
    }

}
