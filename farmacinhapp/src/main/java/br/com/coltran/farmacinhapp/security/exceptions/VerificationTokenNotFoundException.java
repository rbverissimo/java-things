package br.com.coltran.farmacinhapp.security.exceptions;

public class VerificationTokenNotFoundException extends Exception {
    public VerificationTokenNotFoundException(String message) {
        super(message);
    }
}
