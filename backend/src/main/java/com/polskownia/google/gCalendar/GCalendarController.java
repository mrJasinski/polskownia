package com.polskownia.google.gCalendar;

import com.google.api.services.calendar.model.Event;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.google.api.services.calendar.model.Events;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.OffsetDateTime;
import java.util.Set;

@RestController
@RequestMapping("/g-calendar")
class GCalendarController
{
    private final GCalendarService calendarService;

    public GCalendarController(GCalendarService calendarService)
    {
        this.calendarService = calendarService;
    }

    @GetMapping
    public Events getEvents() throws IOException, GeneralSecurityException
    {
        return calendarService.getUpcomingEvents();
    }

    public record CreateEventRequest(
            String title
            , String description
            , OffsetDateTime start
            , OffsetDateTime end
            , Set<String> emails
    ) {}

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody CreateEventRequest request)
    {
        try
        {
            return calendarService.createEvent(
                    request.title(),
                    request.description(),
                    request.start(),
                    request.end()
                    , request.emails()
            );
        }
        catch (IllegalArgumentException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (IOException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Nie udało się utworzyć wydarzenia w Google Calendar");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
