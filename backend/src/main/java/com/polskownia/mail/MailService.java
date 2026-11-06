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
        var subject = "To na pewno nie jest phishing!";

        var text = """
                Cześć!
                
                jestem albańskim wirusem komputerowym. Ze względu na niski poziom rozwoju mojego kraju nie potrafię wyrządzić krzywdy Twojemu sprzętowi. Proszę skasuj samodzielnie ważne pliki systemowe i przekaż tę wiadomość znajomemu. Dziękuję za współpracę!
                """;

        sendSimpleMessage(mail, subject, text);
    }

//    mail z linkiem do spotkania online


}
