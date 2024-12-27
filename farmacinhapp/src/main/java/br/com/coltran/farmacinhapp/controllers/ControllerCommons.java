package br.com.coltran.farmacinhapp.controllers;

import br.com.coltran.farmacinhapp.security.services.AuthService;
import br.com.coltran.farmacinhapp.utils.Datas;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ControllerCommons {

    @Autowired
    protected Datas datasUtils;

    @Autowired
    protected AuthService authService;

}
