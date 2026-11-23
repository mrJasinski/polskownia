package com.polskownia.course;

import com.polskownia.course.dto.LectureReadModel;

import java.util.List;

interface LectureRepository
{
    Lecture save(Lecture toSave);

    List<LectureReadModel> findByCourseRefNumbers(List<String> refNumbers);
}
