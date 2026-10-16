package com.polskownia.mail;

import com.polskownia.utils.Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
class MailConfig
{
    @Bean
    JavaMailSender getJavaMailSender()
    {
        var mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

//        TODO dane konta - aby nie były tak jawne

        mailSender.setUsername(Utils.APP_MAIL);
        mailSender.setPassword("spclbkqewipnpkzj");

        var properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateSimpleMessage()
    {
        var message = new SimpleMailMessage();
        message.setText(
                "This is the test email template!");
        return message;
    }
}
