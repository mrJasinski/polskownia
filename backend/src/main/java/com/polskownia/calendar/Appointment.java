package com.polskownia.calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class Appointment
{
    private int id;
    private String type;
    private int freeMinutesBefore;
    private int freeMinutesAfter;
    private LocalDate appointmentDate;
    private LocalTime startTime;
    private LocalTime endTime;  //czy int durationMinutes?

    LocalTime getStartTime()
    {
        return startTime;
    }

    LocalTime getEndTime()
    {
        return endTime;
    }
}
