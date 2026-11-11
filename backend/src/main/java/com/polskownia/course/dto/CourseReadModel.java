package com.polskownia.course.dto;

import com.polskownia.utils.Level;

import java.util.Set;

public record CourseReadModel(
        String refNumber
        , String name
        , Level level
        , Set<String> emails
//        , Set<LectureReadModel> lectures
)
{
}
