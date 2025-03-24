package br.com.coltran.farmacinhapp.services.interfaces;

import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;

public interface RepositoryService<T> {

    public T findResourceById(long resourceId);
    public boolean isResourceOwner(long resourceId);
    public T update(T managed, T formUpdated);
}
