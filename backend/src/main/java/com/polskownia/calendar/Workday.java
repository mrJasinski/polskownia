package com.polskownia.calendar;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "workdays")
class Workday
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate workdayDate;
    private LocalTime workTimeStart;
    private LocalTime workTimeEnd;
    @OneToMany(mappedBy = "workday", cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<>();

    Workday()
    {
    }

    Workday(final LocalDate workdayDate, final LocalTime workTimeStart, final LocalTime workTimeEnd)
    {
        this.workdayDate = workdayDate;
        this.workTimeStart = workTimeStart;
        this.workTimeEnd = workTimeEnd;
    }

    Workday(final LocalDate workdayDate, final LocalTime workTimeStart, final LocalTime workTimeEnd, final Set<Appointment> appointments)
    {
        this(workdayDate, workTimeStart, workTimeEnd);
        addAppointments(appointments);
    }

    public void addAppointment(final Appointment appointment)
    {
        this.appointments.add(appointment);
    }

    public void addAppointments(final Set<Appointment> appointments)
    {
        this.appointments = appointments;

        this.appointments.forEach(a -> a.setWorkday(this));
    }

    public boolean hasAppointments()
    {
        return (!this.appointments.isEmpty());
    }

    public int getId()
    {
        return this.id;
    }

    public LocalDate getWorkdayDate()
    {
        return this.workdayDate;
    }

    public LocalTime getWorkTimeStart()
    {
        return this.workTimeStart;
    }

    public LocalTime getWorkTimeEnd()
    {
        return this.workTimeEnd;
    }

    public Set<Appointment> getAppointments()
    {
        return this.appointments;
    }
}
