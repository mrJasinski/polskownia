package com.polskownia.calendar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

class WorkDay
{
    private LocalDate day;
    private LocalTime workTimeStart;
    private LocalTime workTimeEnd;
    private List<Appointment> appointments;


    boolean hasAppointments()
    {
        return (!this.appointments.isEmpty());
    }

    LocalDate getDay()
    {
        return this.day;
    }

    LocalTime getWorkTimeStart()
    {
        return this.workTimeStart;
    }

    LocalTime getWorkTimeEnd()
    {
        return this.workTimeEnd;
    }

    List<Appointment> getAppointments()
    {
        return this.appointments;
    }
}
