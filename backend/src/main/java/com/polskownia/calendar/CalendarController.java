package com.polskownia.calendar;

import com.polskownia.calendar.dto.SimpleAppointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/calendar")
class CalendarController
{
    private final CalendarService calendarService;

    CalendarController(final CalendarService calendarService)
    {
        this.calendarService = calendarService;
    }

    @GetMapping()
//    ResponseEntity<?> getAvailableSlotsByDatesAndAppointment(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestBody SimpleAppointment toAppoint)
//    TODO uproszczona wersja dla testów
    ResponseEntity<?> getAvailableSlotsByDatesAndAppointment(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam int durationMinutes)
    {
        System.out.println("wypluwam kalendarz!");

        return ResponseEntity.ok(this.calendarService.getAvailableSlotsByDates(startDate, endDate, durationMinutes));
    }

}
