package br.com.coltran.farmacinhapp.services;

import br.com.coltran.farmacinhapp.domain.Remedio;
import br.com.coltran.farmacinhapp.repositories.RemedioRepository;
import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.services.interfaces.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RemedioService extends ServiceWorker<Remedio> implements RepositoryService {

    @Autowired
    private RemedioRepository remedioRepository;

    public Remedio findResourceById(int remedioId){
        return remedioRepository.findById((long) remedioId).orElse(null);
    }

    public boolean isResourceOwner(long remedioId){
        User usuario = authService.usuarioLogado();
        return usuario.getFarmacias().stream()
                .flatMap(f -> f.getRemedios().stream())
                .anyMatch(r -> r.getId() == remedioId);
    }

    public Remedio save(Remedio remedio){
        timestamps(remedio);
        return remedioRepository.save(remedio);
    }

    public Page<Remedio> getRemediosByFarmacia(Long farmaciaId, Pageable pageable){
        return remedioRepository.findAllByFarmacia(farmaciaId, pageable);
    }

    private void calcularDoses(Remedio remedio){

    }

}
