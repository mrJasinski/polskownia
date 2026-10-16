package com.polskownia.mail;

import com.polskownia.utils.Utils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
class MailService
{
    private final JavaMailSender mailSender;

    MailService(final JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public void sendSimpleMessage(String mailTo, String subject, String text)
    {
        var message = new SimpleMailMessage();

        message.setFrom(Utils.APP_MAIL);
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);

        this.mailSender.send(message);
    }

    //    TODO najbardziej podstawowa metoda do testu

    void sendMail()
    {
        var mail = "abakan@ymail.com";
        var subject = "Mail testowy!";

        var text = """
                To jest mail testowy systemu strony internetowej Żoneczki!
                """;

        sendSimpleMessage(mail, subject, text);
    }

//    mail z linkiem do spotkania online


}
