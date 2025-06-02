package br.com.coltran.farmacinhapp.controllers.api;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.controllers.web.dto.RemedioCatalogoDTO;
import br.com.coltran.farmacinhapp.domain.Remedio;
import br.com.coltran.farmacinhapp.services.FarmaciaService;
import br.com.coltran.farmacinhapp.services.RemedioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<Remedio>> indexByFarmacia(@PathVariable("farmacia_id") long farmaciaId,
                                               @RequestParam(required = false) String nome,
                                               @PageableDefault(size = 12, page = 0) Pageable pageable){
        Page<Remedio> remedios = remedioService.getRemediosByFarmaciaAndNome(farmaciaId, nome, pageable);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(remedios);
    }

    @GetMapping("/catalogo")
    public ResponseEntity<Page<RemedioCatalogoDTO>> indexCatalogo(@RequestParam String nome,
                                                                  @PageableDefault(size = 12, page = 0) Pageable pageable){
        Page<RemedioCatalogoDTO> remedios = remedioService.getCatalogo(nome, pageable);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(remedios);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("@remedioService.isResourceOwner(#id)")
    public ResponseEntity<?> destroy(@PathVariable("id") long id){
        remedioService.deleteResourceById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON).build();
    }


}
