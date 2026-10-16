package com.polskownia.calendar;

import org.springframework.web.bind.annotation.RestController;

@RestController
class CalendarController
{
    private final CalendarService calendarService;

    CalendarController(final CalendarService calendarService)
    {
        this.calendarService = calendarService;
    }


}
