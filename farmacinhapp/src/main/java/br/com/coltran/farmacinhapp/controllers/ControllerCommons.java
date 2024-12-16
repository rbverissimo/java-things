package br.com.coltran.farmacinhapp.controllers;

import br.com.coltran.farmacinhapp.domain.TableEntity;
import br.com.coltran.farmacinhapp.security.services.AuthService;
import br.com.coltran.farmacinhapp.utils.ZonedBrasilTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

public abstract class ControllerCommons {

    @Autowired
    protected ZonedBrasilTime zonedBrasilTime;

    @Autowired
    protected AuthService authService;

    private ZonedDateTime dataHora(){
        return zonedBrasilTime.dataHora();
    }

    protected void setCriacaoAlteracaoAgora(TableEntity te){
        te.setDataCriacao(dataHora());
        te.setDataAlteracao(dataHora());
    }
}
