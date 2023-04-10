package com.crm.sofia.services.common.emailSender;

import com.crm.sofia.dto.settings.SettingsDto;
import com.crm.sofia.exception.CouldNotSaveException;
import com.crm.sofia.exception.DoesNotExistException;
import com.crm.sofia.exception.SendingEmailException;
import com.crm.sofia.repository.settings.SettingsRepository;
import com.crm.sofia.utils.AES_ENCRYPTION;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Predicate;

@Service
public class EmailServiceImpl implements EmailService {

    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    private final SettingsRepository settingsRepository;

    public EmailServiceImpl(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public JavaMailSender getJavaMailSender() {

        SettingsDto emailSettings = Optional.ofNullable(settingsRepository.getEmailSettings("1"))
                .orElseThrow(() -> new DoesNotExistException("Email settings didn't configure"));

        Predicate<SettingsDto> predicate = settingsDto -> StringUtils.isNotEmpty(settingsDto.getMailSenderHost())
                && StringUtils.isNotEmpty(settingsDto.getMailSenderPort())
                && StringUtils.isNotEmpty(settingsDto.getMailSenderUsername())
                && StringUtils.isNotEmpty(settingsDto.getMailSenderPassword());

        if (predicate.test(emailSettings)) {
            mailSender.setHost(emailSettings.getMailSenderHost());
            if (NumberUtils.isCreatable(emailSettings.getMailSenderPort())) {
                int port = Integer.parseInt(emailSettings.getMailSenderPort());
                mailSender.setPort(port);
            } else {
                throw new SendingEmailException("Port must be numeric");
            }


            mailSender.setUsername(emailSettings.getMailSenderUsername());
            try {
                AES_ENCRYPTION aes_encryption = new AES_ENCRYPTION();
                aes_encryption.initFromStrings("+SN6d+OLRgkwDR13dFiq+w==","TDNvUIiNHCGtQKrQ");
                mailSender.setPassword(aes_encryption.decrypt(emailSettings.getMailSenderPassword()));
            } catch (Exception exception) {
                throw new CouldNotSaveException("Email has not been sent. Contact with your system administrator");
            }

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            return mailSender;
        } else {
            throw new SendingEmailException();
        }
    }

    @Override
    public void sendHtmlEmail(String subject,List<String>recipients, String htmlContent) throws MessagingException {
        MimeMessage message = getJavaMailSender().createMimeMessage();
        String commaSeparatedRecipients = StringUtils.join(recipients, ",");
        message.setRecipients(MimeMessage.RecipientType.TO,commaSeparatedRecipients );
        message.setSubject(subject);

        message.setContent(htmlContent, "text/html; charset=utf-8");
        try {
            getJavaMailSender().send(message);
        } catch (MailException mailException) {
            throw new SendingEmailException("Email has not been sent. Contact with your system administrator");
        }

    }
}
