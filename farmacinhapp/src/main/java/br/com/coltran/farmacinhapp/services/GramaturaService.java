package br.com.coltran.farmacinhapp.services;

import br.com.coltran.farmacinhapp.domain.Gramatura;
import br.com.coltran.farmacinhapp.services.interfaces.RepositoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GramaturaService extends ServiceWorker implements RepositoryService<Gramatura> {

    @Override
    public Gramatura findResourceById(long resourceId) {
        return null;
    }

    @Override
    public boolean isResourceOwner(long resourceId) {
        return remediosUsuario().stream().flatMap(remedio -> remedio.getGramaturas().stream()).anyMatch(gramatura -> gramatura.getId() == resourceId);
    }

    @Override
    public Gramatura update(Gramatura managed, Gramatura formUpdated) {
        return null;
    }
}
