package com.polskownia.calendar;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "appointments")
class Appointment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private AppointmentType type;
    private int freeMinutesBefore;
    private int freeMinutesAfter;
    private LocalTime startTime;
    private LocalTime endTime;  //czy int durationMinutes?
    private double price;
    @ManyToOne
    @JoinColumn(name = "workday_id")
    private Workday workday;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    Appointment()
    {
    }

    Appointment(final AppointmentType type, final LocalTime startTime, final LocalTime endTime)
    {
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    Appointment(final AppointmentType type, final LocalTime startTime, final LocalTime endTime, final double price, final Workday workday)
    {
        this(type, startTime, endTime);
        this.price = price;
        this.workday = workday;
    }

    Appointment(final AppointmentType type, final LocalTime startTime, final LocalTime endTime, final double price, final Workday workday, final Customer customer)
    {
        this(type, startTime, endTime, price, workday);
        this.customer = customer;
    }

    LocalTime getStartTime()
    {
        return startTime;
    }

    LocalTime getEndTime()
    {
        return endTime;
    }

    void setWorkday(final Workday workday)
    {
        this.workday = workday;
    }
}
