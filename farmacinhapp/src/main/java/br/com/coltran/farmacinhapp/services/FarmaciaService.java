package br.com.coltran.farmacinhapp.services;

import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.repositories.FarmaciaRepository;
import br.com.coltran.farmacinhapp.services.interfaces.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FarmaciaService extends ServiceWorker<Farmacia> implements RepositoryService {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    public Farmacia findResourceById(int farmaciaId){
        return farmaciaRepository.findById((long) farmaciaId).orElse(null);
    }

    public boolean isResourceOwner(long farmaciaId){
        return usuario().getFarmacias().stream().anyMatch(f ->  f.getId() == farmaciaId);
    }

    public Farmacia save(Farmacia farmacia){
        Optional.ofNullable(farmacia.getPaciente()).ifPresent(this::timestamps);
        timestamps(farmacia);
        return farmaciaRepository.save(farmacia);
    }

}
