package com.polskownia.calendar.dto;

import com.polskownia.calendar.AppointmentType;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentWriteModel
{
    private LocalDate date;
    private LocalTime startTime;
    private AppointmentType type;
    private int durationMinutes;
    private double price;
    private CustomerWriteModel customer;
    private String notes;

    public LocalDate getDate()
    {
        return this.date;
    }

    public LocalTime getStartTime()
    {
        return this.startTime;
    }

    public AppointmentType getType()
    {
        return this.type;
    }

    public int getDurationMinutes()
    {
        return this.durationMinutes;
    }

    public double getPrice()
    {
        return this.price;
    }

    public CustomerWriteModel getCustomer()
    {
        return this.customer;
    }

    public String getNotes()
    {
        return this.notes;
    }
}
