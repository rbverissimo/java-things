package br.com.coltran.farmacinhapp.controllers.api;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.domain.Remedio;
import br.com.coltran.farmacinhapp.services.FarmaciaService;
import br.com.coltran.farmacinhapp.services.RemedioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/remedios")
public class RemediosApiController extends ControllerCommons {

    @Autowired
    private RemedioService remedioService;

    @Autowired
    private FarmaciaService farmaciaService;

    @GetMapping("/{farmacia_id}")
    @PreAuthorize("@farmaciaService.isResourceOwner(#farmaciaId)")
    public ResponseEntity<Page<Remedio>> index(@PathVariable("farmacia_id") long farmaciaId,
                                @RequestParam(required = false) String nome,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "12") int size){
        Page<Remedio> remedios = remedioService.getRemediosByNome(farmaciaId, nome, Pageable.ofSize(size).withPage(page));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(remedios);
    }


}
