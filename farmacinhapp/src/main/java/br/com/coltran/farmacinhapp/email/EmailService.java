package br.com.coltran.farmacinhapp.email;

public interface EmailService {

    void sendMail(EmailDetails emailDetails);
    String sendMailWithAttachment(EmailDetails emailDetails);
}
