package br.com.coltran.farmacinhapp.services;

import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.domain.Remedio;
import br.com.coltran.farmacinhapp.domain.interfaces.TableEntity;
import br.com.coltran.farmacinhapp.security.domain.User;
import br.com.coltran.farmacinhapp.security.services.AuthService;
import br.com.coltran.farmacinhapp.utils.ZonedBrasilTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ServiceWorker {

    @Autowired
    protected ZonedBrasilTime zonedBrasilTime;

    @Autowired
    protected AuthService authService;

    protected User usuario(){
        return authService.usuarioLogado();
    }

    protected List<Remedio> remediosUsuario() {
        return usuario().getFarmacias().stream().flatMap(farmacia -> farmacia.getRemedios().stream()).collect(Collectors.toList());
    }

    protected void timestamps(TableEntity T){
        T.setDataCriacao(zonedBrasilTime.dataHora());
        T.setDataAlteracao(zonedBrasilTime.dataHora());
    }

    protected void updateTimestamp(TableEntity T){
        T.setDataAlteracao(zonedBrasilTime.dataHora());
    }
}
