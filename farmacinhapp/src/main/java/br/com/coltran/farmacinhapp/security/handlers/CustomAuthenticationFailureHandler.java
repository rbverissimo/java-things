package br.com.coltran.farmacinhapp.security.handlers;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final FlashMapManager flashMapManager = new SessionFlashMapManager();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        FlashMap flashMap = new FlashMap();
        String errorMessage = "";
        String failureUrl = "/login?error";

        if(exception instanceof DisabledException){
            errorMessage = "Cadastro não verificado. Confirme seu cadastro antes de continuar.";
            failureUrl = failureUrl.replace("error", "unverified");
        } else if(exception instanceof BadCredentialsException){
            errorMessage = "Credenciais inválidas.";
        } else {
            errorMessage = "Erro encontrado ao fazer login. Por favor, entre em contato com o suporte.";
        }

        flashMap.put("infoErr", errorMessage);
        flashMapManager.saveOutputFlashMap(flashMap, request, response);
        response.sendRedirect(request.getContextPath()+failureUrl);

    }
}
