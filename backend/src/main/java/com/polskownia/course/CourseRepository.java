package com.polskownia.course;

import com.polskownia.course.dto.CourseReadModel;

import java.util.List;
import java.util.Optional;

interface CourseRepository
{
    Course save(Course toSave);

    Optional<Course> findByRefNumber(String refNumber);

    List<Course> findAll();
    List<CourseReadModel> findAllAsReadModel();
}
