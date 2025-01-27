package br.com.coltran.farmacinhapp.services.interfaces;

import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;

public interface RepositoryService {

    public TableEntity findResourceById(long resourceId);
    public boolean isResourceOwner(long resourceId);
}
