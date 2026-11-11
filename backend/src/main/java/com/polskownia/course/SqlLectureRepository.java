package com.polskownia.course;

import com.polskownia.course.dto.LectureReadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlLectureRepository extends LectureRepository, JpaRepository<Lecture, Integer>
{
    @Override
    @Query(value = "SELECT l.course.refNumber, l.lectureDate, l.startTime, l.durationMinutes FROM Lecture l WHERE l.course.refNumber IN :refNumbers")
    List<LectureReadModel> findByCourseRefNumbers(List<String> refNumbers);
}
