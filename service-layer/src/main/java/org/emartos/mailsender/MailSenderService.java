package org.emartos.mailsender;

public interface MailSenderService {

    // TODO Enric void sendEmail(EmailContent emailContent) throws MailSenderException;
    void sendEmail(String to, String subject, String body);

//    boolean isEmailServiceValid();
}
