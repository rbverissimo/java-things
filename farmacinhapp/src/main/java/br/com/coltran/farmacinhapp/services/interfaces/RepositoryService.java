package br.com.coltran.farmacinhapp.services.interfaces;

import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;

public interface RepositoryService {

    public TableEntity findResourceById(int resourceId);
    public boolean isResourceOwner(long resourceId);
}
