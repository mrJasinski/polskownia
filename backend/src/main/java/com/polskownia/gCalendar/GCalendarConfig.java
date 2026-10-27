package com.polskownia.gCalendar;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class GCalendarConfig
{
    @Value("${google.calendar.credentials}")
    private String credentialsPath;

    @Bean
    public Calendar googleCalendar() throws Exception
    {
        GoogleCredentials credentials;

        try (var inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(this.credentialsPath))
        {
            if (inputStream == null)
                throw new IllegalStateException("Nie znaleziono pliku: " + this.credentialsPath);


            credentials = ServiceAccountCredentials
                    .fromStream(inputStream)
                    .createScoped(List.of(CalendarScopes.CALENDAR));
        }

        return new Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport()
                , GsonFactory.getDefaultInstance()
                , new HttpCredentialsAdapter(credentials)
                )
                .setApplicationName("Polskownia")
                .build();
    }
}
