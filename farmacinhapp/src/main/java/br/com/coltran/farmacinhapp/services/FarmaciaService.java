package br.com.coltran.farmacinhapp.services;

import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.repositories.FarmaciaRepository;
import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmaciaService {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private AuthService authService;

    public Farmacia findFarmaciaById(int id){
        return farmaciaRepository.findById((long) id).orElse(null);
    }

    public boolean isResourceOwner(int farmaciaId){
        User user = authService.usuarioLogado();
        return user.getFarmacias().stream().anyMatch(f ->  f.getId() == farmaciaId);
    }

}
