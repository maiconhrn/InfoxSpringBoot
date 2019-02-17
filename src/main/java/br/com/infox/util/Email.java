package br.com.infox.util;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class Email {

    private final String HOST = "smtp.gmail.com";
    private final String USER = "infoxmanutencao@gmail.com";
    private final String PASS = "infoxmanutencao";
    private final String FROM = "infoxmanutencao@gmail.com";
    private final boolean SESSIONDEBUG = false;
    private String to;
    private String subject;
    private String messageText;

    public Email(String to, String subject, String messageText) {
        super();
        this.to = to;
        this.subject = subject;
        this.messageText = messageText;
    }

    public void sendEmail() {
        try {
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", HOST);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smpt.starttls.enable", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(SESSIONDEBUG);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(messageText);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(HOST, USER, PASS);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex);
        }
    }

}
