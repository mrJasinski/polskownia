package com.polskownia.google.gMail;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

import java.util.Base64;
import java.util.Properties;

@RestController
@RequestMapping("/api/gmail")
class GMailController
{
    private final GoogleAuthorizationCodeFlow gmailFlow;
    private final GMailService gmailService;


    private static final String ACCOUNT_ID = "global-gmail";

    public GMailController(GoogleAuthorizationCodeFlow gmailFlow, final GMailService gmailService)
    {
        this.gmailFlow = gmailFlow;
        this.gmailService = gmailService;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest request) throws Exception
    {
        var credential = this.gmailFlow.loadCredential(ACCOUNT_ID);

        if (credential == null)
            throw new IllegalStateException("Gmail account is not authorized");


        var gmail = new Gmail.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                credential
        )
                .setApplicationName("Polskownia")
                .build();

        var email = new MimeMessage(Session.getDefaultInstance(new Properties()));

        email.setFrom(request.getFrom());
        email.setRecipients(
                jakarta.mail.Message.RecipientType.TO,
                request.getTo()
        );
        email.setSubject(request.getSubject());
        email.setText(request.getBody(), "UTF-8");

        var buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);

        String encodedEmail = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(buffer.toByteArray());

        var message = new Message();
        message.setRaw(encodedEmail);

        gmail.users().messages()
                .send("me", message)
                .execute();

        return "Email sent successfully";
    }

//    @GetMapping("/connect")
//    public ResponseEntity<Void> connect(HttpSession session)
//    {
//        String authorizationUrl = gmailService.generateAuthorizationUrl(session);
//
//        return ResponseEntity
//                .status(HttpStatus.FOUND)
//                .location(URI.create(authorizationUrl))
//                .build();
//    }

//    @GetMapping("/callback")
//    public ResponseEntity<String> callback(@RequestParam String code, @RequestParam String state, HttpSession session) throws GeneralSecurityException, IOException
//    {
//        gmailService.handleCallback(code, state, session);
//
//        return ResponseEntity.ok("Gmail connected successfully");
//    }
//
    public static class EmailRequest
    {
        private String from;
        private String to;
        private String subject;
        private String body;

        public String getFrom()
        {
            return from;
        }

        public void setFrom(String from)
        {
            this.from = from;
        }

        public String getTo()
        {
            return to;
        }

        public void setTo(String to)
        {
            this.to = to;
        }

        public String getSubject()
        {
            return subject;
        }

        public void setSubject(String subject)
        {
            this.subject = subject;
        }

        public String getBody()
        {
            return body;
        }

        public void setBody(String body)
        {
            this.body = body;
        }
    }
}
