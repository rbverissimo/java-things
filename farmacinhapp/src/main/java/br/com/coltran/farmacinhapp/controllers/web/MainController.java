package br.com.coltran.farmacinhapp.controllers.web;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.controllers.web.dto.RemedioCatalogoDTO;
import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.domain.Remedio;
import br.com.coltran.farmacinhapp.domain.valueobjects.RemedioIndexVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MainController extends ControllerCommons {

    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/")
    public String home(Model model){

        Set<RemedioIndexVO> remedios = authService.usuarioLogado()
                        .getFarmacias().stream()
                        .flatMap(f -> f.getRemedios().stream()
                                .map(r -> { return new RemedioIndexVO.Builder().buildFromModel(r);}))
                .collect(Collectors.toSet());

        model.addAttribute("usuario", authService.usuarioLogado());
        model.addAttribute("remedios", remedios);


        return "index";
    }
}
