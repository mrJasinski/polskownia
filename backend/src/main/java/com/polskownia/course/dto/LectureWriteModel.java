package com.polskownia.course.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class LectureWriteModel
{
    private String courseRefNumber;
    private LocalDate lectureDate;
    private LocalTime startTime;
    private int durationMinutes;

    public String getCourseRefNumber()
    {
        return this.courseRefNumber;
    }

    public LocalDate getLectureDate()
    {
        return this.lectureDate;
    }

    public LocalTime getStartTime()
    {
        return this.startTime;
    }

    public int getDurationMinutes()
    {
        return this.durationMinutes;
    }
}
