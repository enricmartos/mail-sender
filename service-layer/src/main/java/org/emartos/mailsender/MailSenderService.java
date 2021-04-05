package org.emartos.mailsender;

public interface MailSenderService {

    void sendEmail(String to, String subject, String body);

}
