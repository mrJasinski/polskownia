package com.polskownia.course.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record LectureReadModel(
        String courseRefNumber
        , LocalDate lectureDate
        , LocalTime startTime
        , int durationMinutes
)
{
}
