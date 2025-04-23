package br.com.coltran.farmacinhapp.controllers.web;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.domain.Remedio;
import br.com.coltran.farmacinhapp.domain.valueobjects.ErroMsgVO;
import br.com.coltran.farmacinhapp.domain.valueobjects.RemedioIndexVO;
import br.com.coltran.farmacinhapp.domain.valueobjects.interfaces.Mensagem;
import br.com.coltran.farmacinhapp.repositories.MedidaRepository;
import br.com.coltran.farmacinhapp.repositories.TipoRemedioRepository;
import br.com.coltran.farmacinhapp.services.FarmaciaService;
import br.com.coltran.farmacinhapp.services.RemedioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/remedios")
public class RemediosController extends ControllerCommons {


    @Autowired
    private RemedioService remedioService;

    @Autowired
    private FarmaciaService farmaciaService;

    @Autowired
    private TipoRemedioRepository tipoRemedioRepository;

    @Autowired
    private MedidaRepository medidaRepository;

    @GetMapping("/i/{farmacia_id}")
    @PreAuthorize("@farmaciaService.isResourceOwner(#farmaciaId)")
    public String indexByFarmacia(@PathVariable("farmacia_id") long farmaciaId, Model model){
        Page<Remedio> remediosPage = remedioService.getRemediosByFarmacia(farmaciaId, Pageable.ofSize(12).withPage(0));

        List<RemedioIndexVO> content = remediosPage.stream()
                        .map(p -> { return new RemedioIndexVO.Builder().buildFromModel(p); })
                        .filter(vo -> vo.getDosesRestantes() > 0)
                                .collect(Collectors.toList());

        boolean canSave = remedioService.canSaveRemedio();

        model.addAttribute("farmaciaId", farmaciaId);
        model.addAttribute("remedios", content);
        model.addAttribute("canSave", canSave);

        return "remedios/index";
    }

    @GetMapping("/cadastro/{farmacia_id}")
    @PreAuthorize("@farmaciaService.isResourceOwner(#farmaciaId)")
    public String cadastroGET(@PathVariable("farmacia_id") long farmaciaId, @ModelAttribute Remedio remedio, Model model){
        model.addAttribute("farmaciaId", farmaciaId);
        model.addAttribute("tiposRemedio", tipoRemedioRepository.findAll());
        return "remedios/cadastro";
    }

    @PostMapping("/cadastro/{farmacia_id}")
    @PreAuthorize("@farmaciaService.isResourceOwner(#farmaciaId)")
    public String cadastroPOST(@PathVariable("farmacia_id") long farmaciaId, @Valid @ModelAttribute Remedio remedio, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("farmaciaId", farmaciaId);
            model.addAttribute("tiposRemedio", tipoRemedioRepository.findAll());
            return "/remedios/cadastro";
        }

        if(authService.usuarioLogado().getFarmacias().stream()
                .flatMap(farmacia -> farmacia.getRemedios().stream())
                .count() > 19){
            model.addAttribute("farmaciaId", farmaciaId);
            model.addAttribute("tiposRemedio", tipoRemedioRepository.findAll());
            model.addAttribute("mensagens", new ArrayList<Mensagem>(){{add(new ErroMsgVO("O número de remédios cadastrados foi excedido"));}});
            return "/remedios/cadastro";
        }

        Farmacia farmacia = farmaciaService.findResourceById(farmaciaId);
        if(farmacia != null) remedio.setFarmacia(farmacia);

        remedioService.save(remedio);
        return "redirect:/remedios/show/"+remedio.getId();
    }

    @GetMapping("/show/{remedio_id}")
    @PreAuthorize("@remedioService.isResourceOwner(#remedioId)")
    public String show(@PathVariable("remedio_id") long remedioId, Model model){
        Remedio remedio = remedioService.findResourceById(remedioId);
        model.addAttribute("remedio", remedio);
        model.addAttribute("gramaturas", remedio.getGramaturas());
        model.addAttribute("farmaciaId", remedio.getFarmacia().getId());
        model.addAttribute("remedioId", remedio.getId());
        model.addAttribute("tiposRemedio", tipoRemedioRepository.findAll());
        return "/remedios/cadastro";
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("@remedioService.isResourceOwner(#id)")
    public String destroy(@PathVariable("id") long id){
        Remedio remedio = remedioService.findResourceById(id);
        long farmaciaId = remedio.getFarmacia().getId();
        remedioService.deleteResourceById(remedio.getId());
        return "redirect:/remedios/i/"+farmaciaId;
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("@remedioService.isResourceOwner(#id)")
    public String edit(@PathVariable("id") long id, @Valid @ModelAttribute Remedio remedio, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) return "/remedios/show/"+id;
        Remedio managedRemedio = remedioService.findResourceById(id);
        if(managedRemedio != null) remedioService.update(managedRemedio, remedio);
        return "redirect:/remedios/show/"+id;
    }

    @GetMapping("/catalogo/")
    public String showCatalogo(Model model){

        Set<Remedio> remedios = authService.usuarioLogado().getFarmacias()
                .stream().flatMap(farmacia -> farmacia.getRemedios().stream()).collect(Collectors.toSet());
        model.addAttribute("remedios", remedios);

        return "";
    }
}
