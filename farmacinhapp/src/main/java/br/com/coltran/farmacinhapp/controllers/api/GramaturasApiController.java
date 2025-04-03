package br.com.coltran.farmacinhapp.controllers.api;

import br.com.coltran.farmacinhapp.controllers.ControllerCommons;
import br.com.coltran.farmacinhapp.domain.Gramatura;
import br.com.coltran.farmacinhapp.services.GramaturaService;
import br.com.coltran.farmacinhapp.services.RemedioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/gramaturas")
public class GramaturasApiController extends ControllerCommons {

    @Autowired
    private RemedioService remedioService;

    @Autowired
    private GramaturaService gramaturaService;

    @GetMapping("/")
    public ResponseEntity<Page<Gramatura>> index(@RequestParam(required = false) String principioAtivo,
                                                 @PageableDefault(size = 12) Pageable pageable){

        Page<Gramatura> gramaturas = Optional.ofNullable(principioAtivo)
                .filter(pa -> !StringUtils.isBlank(pa))
                .map(pa -> gramaturaService.findByPrincipioAtivo(pa, pageable))
                .orElseGet(() -> gramaturaService.findByUsuario(pageable));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(gramaturas);
    }

    @GetMapping("/{remedio_id}")
    @PreAuthorize("@remedioService.isResourceOwner(#remedioId)")
    public ResponseEntity<Page<Gramatura>> indexByRemedio(@PathVariable("remedio_id") long remedioId,
                                                          @RequestParam(required = false) String principioAtivo,
                                                          @PageableDefault(size = 12)Pageable pageable){

        Page<Gramatura> gramaturas = Optional.ofNullable(principioAtivo)
                .filter(pa -> !StringUtils.isBlank(pa))
                .map(pa -> gramaturaService.findByPrincipioAtivo(pa, remedioId, pageable))
                .orElseGet(() -> gramaturaService.findByRemedioId(remedioId, pageable));

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(gramaturas);
    }

}
