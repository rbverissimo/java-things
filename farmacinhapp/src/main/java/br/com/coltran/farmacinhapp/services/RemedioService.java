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

    public Remedio findResourceById(long remedioId){
        return remedioRepository.findById(remedioId).orElse(null);
    }

    public void deleteResourceById(long remedioId){
        remedioRepository.deleteById(remedioId);
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

    public Page<Remedio> getRemediosByNome(Long farmaciaId, String nome, Pageable pageable){
        return remedioRepository.findByNome(farmaciaId, nome, pageable);
    }

    private void calcularDoses(Remedio remedio){

    }

}
