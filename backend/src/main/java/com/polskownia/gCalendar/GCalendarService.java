package com.polskownia.gCalendar;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

@Service
class GCalendarService
{
    private final com.google.api.services.calendar.Calendar calendar;

    @Value("${google.calendar.id}")
    private String calendarId;

    @Value("${google.calendar.credentials}")
    private String credentialsPath;

    public GCalendarService(com.google.api.services.calendar.Calendar calendar)
    {
        this.calendar = calendar;
    }

    public Event createEvent(
            String title,
            String description,
            OffsetDateTime start,
            OffsetDateTime end
    ) throws IOException {

        if (!end.isAfter(start)) {
            throw new IllegalArgumentException(
                    "Koniec wydarzenia musi być późniejszy niż początek"
            );
        }

        Event event = new Event()
                .setSummary(title)
                .setDescription(description)
                .setStart(new EventDateTime()
                        .setDateTime(new com.google.api.client.util.DateTime(
                                Date.from(start.toInstant())))
                        .setTimeZone(ZoneId.of("Europe/Warsaw").getId()))
                .setEnd(new EventDateTime()
                        .setDateTime(new com.google.api.client.util.DateTime(
                                Date.from(end.toInstant())))
                        .setTimeZone(ZoneId.of("Europe/Warsaw").getId()));

        return calendar.events()
                .insert(calendarId, event)
                .execute();
    }

    public Events getUpcomingEvents()
            throws IOException, GeneralSecurityException {

        GoogleCredentials credentials;

        try (var inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("service-account.json")) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        "Nie znaleziono service-account.json w resources"
                );
            }

            credentials = ServiceAccountCredentials
                    .fromStream(inputStream)
                    .createScoped(
                            List.of(CalendarScopes.CALENDAR)
                    );
        }

        Calendar service = new Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials)
        )
                .setApplicationName("Calendar App")
                .build();
//TODO pobieranie początku tygodnia z przesłanej daty
        return service.events()
                .list(calendarId)
                .setTimeMin(
                        new com.google.api.client.util.DateTime(
                                LocalDate.now()
                                        .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                                        .atStartOfDay(ZoneId.systemDefault())
                                        .toInstant()
                                        .toEpochMilli()
                        )
                )
                .setMaxResults(250)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
    }
}
