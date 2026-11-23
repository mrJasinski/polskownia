package com.polskownia.course;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "lectures")
class Lecture
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate lectureDate;
    private LocalTime startTime;
    private int durationMinutes;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    Lecture()
    {
    }

    Lecture(final LocalDate lectureDate, final LocalTime startTime, final int durationMinutes)
    {
        this.lectureDate = lectureDate;
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
    }

    Lecture(final LocalDate lectureDate, final LocalTime startTime, final int durationMinutes, final Course course)
    {
        this(lectureDate, startTime, durationMinutes);
        this.course = course;
    }

    public OffsetDateTime getOffsetStartTime()
    {
        return this.lectureDate.atTime(this.startTime).atZone(ZoneId.of("Europe/Warsaw")).toOffsetDateTime();
    }

    public OffsetDateTime getOffsetEndTime()
    {
        return this.lectureDate.atTime(this.startTime.plusMinutes(this.durationMinutes)).atZone(ZoneId.of("Europe/Warsaw")).toOffsetDateTime();
    }

    public int getId()
    {
        return this.id;
    }

    public LocalDate getLectureDate()
    {
        return this.lectureDate;
    }

    public LocalTime getStartTime()
    {
        return this.startTime;
    }

    public LocalTime getEndTime()
    {
        return this.startTime.plusMinutes(this.durationMinutes);
    }

    public int getDurationMinutes()
    {
        return this.durationMinutes;
    }

    public Course getCourse()
    {
        return this.course;
    }

    void setCourse(final Course course)
    {
        this.course = course;
    }
}
