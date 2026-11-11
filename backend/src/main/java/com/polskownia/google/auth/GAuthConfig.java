package com.polskownia.google.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.List;

@Configuration
class GAuthConfig
{
    @Bean
    public GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow() throws IOException, GeneralSecurityException
    {
        var transport = GoogleNetHttpTransport.newTrustedTransport();

        var jsonFactory = GsonFactory.getDefaultInstance();

        GoogleClientSecrets clientSecrets;

        try (var input = new ClassPathResource("google-oauth.json").getInputStream())
        {
            clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(input, StandardCharsets.UTF_8));
        }

        return new GoogleAuthorizationCodeFlow.Builder(
                    transport,
                    GsonFactory.getDefaultInstance(),
                    clientSecrets,
                    List.of(CalendarScopes.CALENDAR))
                .setDataStoreFactory(new FileDataStoreFactory(new File("google-tokens")))
                .setAccessType("offline")
                .build();
    }
}
