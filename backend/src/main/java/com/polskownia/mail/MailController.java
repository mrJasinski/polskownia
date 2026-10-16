package com.polskownia.mail;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MailController
{
    private final MailService mailService;

    MailController(final MailService mailService)
    {
        this.mailService = mailService;
    }

//    TODO najbardziej podstawowa metoda do testu

    @GetMapping("/mailTest")
    ResponseEntity<?> mailTest()
    {
        this.mailService.sendMail();

        return ResponseEntity.ok("Mail sent!");
    }

}
