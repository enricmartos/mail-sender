package org.emartos.mailsender.rest.v1;

import org.emartos.mailsender.MailSenderService;
import org.emartos.mailsender.config.PropertiesConfig;
import org.emartos.mailsender.rest.v1.utils.ServiceControllerValidationHelper;
import org.emartos.mailsender.v1.exceptions.BadRequestException;
import org.emartos.mailsender.v1.exceptions.MailNotSendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class MailSenderServiceController implements org.emartos.mailsender.v1.MailSenderService {

    private static final Logger LOGGER = Logger.getLogger(MailSenderServiceController.class.getName());

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Override
    public void sendEmail(String apiKey, String to, String subject, String body) throws BadRequestException, MailNotSendException {

        validateApiKey(apiKey);
        validateEmailContent(to, subject, body);
        mailSenderService.sendEmail(to, subject, body);


    }

    private void validateApiKey(String apiKey) throws BadRequestException {
        if (apiKey == null || !propertiesConfig.getApiKeys().contains(apiKey)) {
            throw new BadRequestException("Invalid API key: " + apiKey);
        }
    }

    private void validateEmailContent(String to, String subject, String body) throws BadRequestException {
        new ServiceControllerValidationHelper("MailSenderService")
                .checkNotNullOrEmpty(to, "To")
                .checkNotNullOrEmpty(subject, "Subject")
                .checkNotNullOrEmpty(body, "Body");
    }
}
