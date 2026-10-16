package com.polskownia.calendar;

import com.polskownia.calendar.dto.SimpleAppointment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
class CalendarService
{
    void getPossibleTerms(final LocalDate startDate, final LocalDate endDate, final SimpleAppointment toAppoint)
    {
        var workdays = getWorkDaysByDates(startDate, endDate);

//                metoda wypluwa dostępne terminy danego dnia
        this.getAvailableTerms(toAppoint);
    }

    List<WorkDay> getWorkDaysByDates(final LocalDate startDate, final LocalDate endDate)
    {
//        TODO
        return null;
    }



    void getAvailableTerms(final SimpleAppointment toAppoint)
    {
        var days = new ArrayList<WorkDay>();

        for (WorkDay day : days)
        {
            if (!day.hasAppointments())
            {
//                prosta iteracja przez cały dzień tak, aby ostatni termin kończył się równo z dniem pracy
            }
            else
            {
                getAvailableSlots(day, toAppoint);
            }
        }
    }

    void getAvailableSlots(final WorkDay day, final SimpleAppointment toAppoint)
    {
        var stepMinutes = 15;

        var slots = new ArrayList<LocalTime>();

        for (LocalTime startAt = day.getWorkTimeStart(); !startAt.plusMinutes(toAppoint.getDurationMinutes()).isAfter(day.getWorkTimeEnd()); startAt = startAt.plusMinutes(stepMinutes))
        {
            var finalStartAt = startAt;
            var endAt = startAt.plusMinutes(toAppoint.getDurationMinutes());

            var occupied = day.getAppointments()
                    .stream()
                    .anyMatch(a -> finalStartAt.isBefore(a.getEndTime()) && endAt.isAfter(a.getStartTime()));

            if (!occupied)
                slots.add(startAt);
        }
    }
}

