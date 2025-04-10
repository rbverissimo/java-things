package br.com.coltran.farmacinhapp.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.from}")
    private String sender;

    @Value("${app.verification.base-url}")
    private String envUrl;


    /**
     * Este método recebe o path para o template de email e um mapa com as variáveis que comporão o email
     * @param templatePath path baseado em resources/templates/
     * @param variables um map no qual a chave é o nome da variável que será que passada ao template
     * @return um template processado pela engine do Thymeleaf
     */
    private String processEmailTemplate(String templatePath, Map<String, Object> variables){
        Context thymeleafContext = new Context();
        if(!CollectionUtils.isEmpty(variables)) thymeleafContext.setVariables(variables);
        return templateEngine.process(templatePath, thymeleafContext);
    }

    public void sendEmailVerification(String recipientEmail, String recipientName, String urlToVerify){
        Map<String, Object> variables = Map.of(
                "username", recipientName,
                "urlVerify", envUrl+urlToVerify
        );

        String htmlBody = processEmailTemplate("emails/verification", variables);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(recipientEmail);
        emailDetails.setMsgBody(htmlBody);
        emailDetails.setSubject("Confirmação de email Farmacinhapp");

        sendMail(emailDetails);

    }

    public void sendFarmaciaShare(String recipientEmail, String recipientName, String sharingUser, String urlShare){
        Map<String, Object> variables = Map.of(
                "username", recipientName,
                "sharingUser", sharingUser,
                "urlShare", envUrl+urlShare
        );

        String htmlBody = processEmailTemplate("emails/shared-farmacia", variables);

        EmailDetails emailDetails = new EmailDetails.Builder()
                .recipient(recipientEmail)
                .msgBody(htmlBody)
                .subject("Oba! Alguém compartilhou uma farmácia com você!")
                .build();

        sendMail(emailDetails);
    }

    @Override
    public void sendMail(EmailDetails details) {

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(details.getRecipient());
            helper.setFrom(sender);
            helper.setSubject(details.getSubject());
            helper.setText(details.getMsgBody(), details.isHtml());

            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException | MailException e) {
            e.printStackTrace(System.err);
        }
    }



    @Override
    public String sendMailWithAttachment(EmailDetails emailDetails) {
        return "";
    }
}
