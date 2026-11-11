package com.polskownia.course;

import com.polskownia.course.dto.CourseReadModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlCourseRepository extends CourseRepository, JpaRepository<Course, Integer>
{
    @Override
    @Query(value = "SELECT c.refNumber, c.name, c.level, c.emails FROM Course c")
//    @Query(value = "SELECT c.refNumber, c.name, c.level, c.emails, c.lectures FROM Course c")
    List<CourseReadModel> findAllAsReadModel();
}