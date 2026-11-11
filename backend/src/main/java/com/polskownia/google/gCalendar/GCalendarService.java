package com.polskownia.google.gCalendar;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
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
import java.util.Set;
import java.util.UUID;

@Service
public class GCalendarService
{

    @Value("${google.calendar.id}")
    private String calendarId;

//    @Value("${google.calendar.credentials}")
//    private String credentialsPath;

    private final GoogleAuthorizationCodeFlow flow;

    public GCalendarService(final GoogleAuthorizationCodeFlow flow)
    {
        this.flow = flow;
    }

    public Event createEvent(
            String title,
            String description,
            OffsetDateTime start,
            OffsetDateTime end
            , Set<String> attendeeEmails) throws Exception
    {
        var requestId = UUID.randomUUID().toString();

        if (!end.isAfter(start))
            throw new IllegalArgumentException("Koniec wydarzenia musi być późniejszy niż początek");

        var event = new Event()
                .setSummary(title)
                .setDescription(description)
                .setStart(new EventDateTime()
                        .setDateTime(new com.google.api.client.util.DateTime(
                                Date.from(start.toInstant())))
                        .setTimeZone(ZoneId.of("Europe/Warsaw").getId()))
                .setEnd(new EventDateTime()
                        .setDateTime(new com.google.api.client.util.DateTime(
                                Date.from(end.toInstant())))
                        .setTimeZone(ZoneId.of("Europe/Warsaw").getId()))
                .setAttendees(attendeeEmails.stream()
                        .map(email -> new EventAttendee().setEmail(email))
                        .toList())
                .setConferenceData(new ConferenceData()
                        .setCreateRequest(new CreateConferenceRequest()
                                .setRequestId(requestId)
                                .setConferenceSolutionKey(
                                        new ConferenceSolutionKey()
                                                .setType("hangoutsMeet")
                                )
                        )
                );

        return getCalendar().events()
                .insert(calendarId, event)
                .execute();
    }

    Events getUpcomingEvents() throws IOException, GeneralSecurityException
    {
        GoogleCredentials credentials;

        try (var inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("service-account.json"))
        {

            if (inputStream == null)
                throw new IllegalStateException("Nie znaleziono service-account.json w resources");

            credentials = ServiceAccountCredentials
                    .fromStream(inputStream)
                    .createScoped(List.of(CalendarScopes.CALENDAR));
        }

        var service = new Calendar.Builder(
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

    public Events getEventsByDates(final LocalDate startDate, final LocalDate endDate) throws IOException, GeneralSecurityException
    {
        GoogleCredentials credentials;

        try (var inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("service-account.json"))
        {

            if (inputStream == null)
                throw new IllegalStateException("Nie znaleziono service-account.json w resources");

            credentials = ServiceAccountCredentials
                    .fromStream(inputStream)
                    .createScoped(List.of(CalendarScopes.CALENDAR));
        }

        var service = new Calendar
                .Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    new HttpCredentialsAdapter(credentials))
                .setApplicationName("Calendar App")
                .build();

        var zoneId = ZoneId.systemDefault();

        var timeMin = new com.google.api.client.util.DateTime(
                        startDate.atStartOfDay(zoneId)
                                .toInstant()
                                .toEpochMilli());

        var timeMax = new com.google.api.client.util.DateTime(
                        endDate.plusDays(1)
                                .atStartOfDay(zoneId)
                                .toInstant()
                                .toEpochMilli());

        return service
                .events()
                    .list(calendarId)
                    .setTimeMin(timeMin)
                    .setTimeMax(timeMax)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();
    }

    Calendar getCalendar() throws Exception
    {
        var credential = flow.loadCredential("gmail-user");

        if (credential == null)
            throw new IllegalStateException("Google Calendar not authorized. Visit /oauth2/authorize");

        return new Calendar
                .Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    credential)
                .setApplicationName("Spring Calendar")
                .build();
    }
}
