package br.com.coltran.farmacinhapp.controllers;

import br.com.coltran.farmacinhapp.email.EmailServiceImpl;
import br.com.coltran.farmacinhapp.security.services.AuthService;
import br.com.coltran.farmacinhapp.utils.Datas;
import br.com.coltran.farmacinhapp.utils.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ControllerCommons {

    @Autowired
    protected Datas datasUtils;

    @Autowired
    protected AuthService authService;

    @Autowired
    protected EmailServiceImpl emailService;

    @Autowired
    protected MessageProcessor messageProcessor;

}
