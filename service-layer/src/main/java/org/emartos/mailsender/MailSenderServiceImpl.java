package org.emartos.mailsender;

import org.emartos.mailsender.config.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    private static final Logger LOGGER = Logger.getLogger(MailSenderServiceImpl.class.getName());

    @Autowired
    private PropertiesConfig propertiesConfig;


    @Override
    public void sendEmail(String to, String subject, String body) {
        Session session = getSession();
        try {
            sendEmail(session, to, subject, body);
        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, "Messaging Exception", e);
        }

    }

    private Session getSession(){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", propertiesConfig.getProperty("mail.smtp.host"));
        properties.put("mail.smtp.port", propertiesConfig.getProperty("mail.smtp.port"));
        properties.put("mail.smtp.auth", propertiesConfig.getProperty("mail.smtp.auth"));
        properties.put("mail.smtp.starttls.enable", propertiesConfig.getProperty("mail.smtp.starttls.enable"));

        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(propertiesConfig.getProperty("mail.smtp.username"),
                        propertiesConfig.getProperty("mail.smtp.password"));
            }
        };

        return Session.getInstance(properties, authenticator);
    }

    private void sendEmail(Session session, String to, String subject, String body) throws MessagingException {
//    private void sendEmail(Session session, String from, List<String> to, String subject, String body) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.setFrom(new InternetAddress(propertiesConfig.getProperty("mail.smtp.username")));
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

//        for (String tos : to) {
//            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(tos));
//        }

        msg.setContent(body, "text/html; charset=UTF-8");
        Transport.send(msg);
    }


}
