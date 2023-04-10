package com.crm.sofia.services.common.emailSender;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

public interface EmailService {
    void sendHtmlEmail(String subject,List<String> recipients, String htmlContent) throws MessagingException;


}
