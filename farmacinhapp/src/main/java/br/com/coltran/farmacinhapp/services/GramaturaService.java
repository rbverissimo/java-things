package br.com.coltran.farmacinhapp.services;

import br.com.coltran.farmacinhapp.domain.Gramatura;
import br.com.coltran.farmacinhapp.repositories.GramaturaRepository;
import br.com.coltran.farmacinhapp.services.interfaces.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GramaturaService extends ServiceWorker implements RepositoryService<Gramatura> {

    @Autowired
    private GramaturaRepository gramaturaRepository;

    @Override
    public Gramatura findResourceById(long resourceId) {
        return gramaturaRepository.findById(resourceId).orElse(null);
    }

    @Override
    public boolean isResourceOwner(long resourceId) {
        return remediosUsuario().stream().flatMap(remedio -> remedio.getGramaturas().stream()).anyMatch(gramatura -> gramatura.getId() == resourceId);
    }


    public Page<Gramatura> findByUsuario(Pageable pageable){
        long usuarioId = usuario().getId();
        return null;
    }

    public Page<Gramatura> findByRemedioId(long remedioId, Pageable pageable){
        return gramaturaRepository.findByRemedioId(remedioId, pageable);
    }

    public Page<Gramatura> findByPrincipioAtivo(String princioAtivo, long remedioId, Pageable pageable){
        return null;
    }

    public Page<Gramatura> findByPrincipioAtivo(String princioAtivo, Pageable pageable){
        long usuarioId = usuario().getId();
        return null;
    }

    @Override
    public Gramatura update(Gramatura managed, Gramatura formUpdated) {
        return null;
    }

    public Gramatura save(Gramatura gramatura){
        timestamps(gramatura);
        return gramaturaRepository.save(gramatura);
    }

    public void deleteFromRemedio(long gramaturaId, long remedioId){
        try{
            gramaturaRepository.deleteGramaturaFromRemedio(gramaturaId, remedioId);
        } catch (Exception ex){
            ex.printStackTrace(System.err);
        }
    }
}
