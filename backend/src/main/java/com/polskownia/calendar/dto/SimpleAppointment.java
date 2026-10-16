package com.polskownia.calendar.dto;

public class SimpleAppointment
{
    private String type;
    private int durationMinutes;
    private int freeMinutesBefore;
    private int freeMinutesAfter;

    String getType()
    {
        return type;
    }

    public int getDurationMinutes()
    {
        return durationMinutes;
    }

    int getFreeMinutesBefore()
    {
        return freeMinutesBefore;
    }

    int getFreeMinutesAfter()
    {
        return freeMinutesAfter;
    }
}
