package br.com.coltran.farmacinhapp.services;

import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.exceptions.FarmaciaException;
import br.com.coltran.farmacinhapp.repositories.FarmaciaRepository;
import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.services.interfaces.RepositoryService;
import br.com.coltran.farmacinhapp.utils.Colecoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class FarmaciaService extends ServiceWorker implements RepositoryService<Farmacia> {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private Colecoes.SET<User> colecoesUser;

    public Farmacia findResourceById(long farmaciaId){
        return farmaciaRepository.findById(farmaciaId).orElse(null);
    }

    public boolean isResourceOwner(long farmaciaId){
        return usuario().getFarmacias().stream().anyMatch(f ->  f.getId() == farmaciaId);
    }

    public Farmacia save(Farmacia farmacia){
        Optional.ofNullable(farmacia.getPaciente()).ifPresent(this::timestamps);
        timestamps(farmacia);
        return farmaciaRepository.save(farmacia);
    }

    public Farmacia update(Farmacia managed, Farmacia formUpdated){
        updateTimestamp(managed);
        managed.setNome(formUpdated.getNome());
        managed.setBio(formUpdated.getBio());
        return farmaciaRepository.save(managed);
    }

    public void compartilharComUsuario(String nomeFarmacia, String email) throws FarmaciaException, UsernameNotFoundException {
        farmaciaRepository.findByNome(nomeFarmacia).ifPresentOrElse(farmacia -> {
            User user = authService.usuarioByEmail(email).orElseThrow(() -> {throw new UsernameNotFoundException("Usuário não encontrado");});
            farmacia.setUsers(colecoesUser.addIfNull(farmacia.getUsers(), user));
            farmaciaRepository.save(farmacia);
        }, () -> { throw new FarmaciaException("Farmácia não encontrada.");});
    }

}
