package com.polskownia.course;

import com.polskownia.course.dto.CourseFullReadModel;
import com.polskownia.course.dto.CourseReadModel;
import com.polskownia.course.dto.LectureReadModel;
import com.polskownia.course.dto.LectureWriteModel;
import com.polskownia.google.gCalendar.GCalendarService;
import com.polskownia.mail.MailService;
import com.polskownia.utils.Level;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class CourseService
{
    private final CourseRepository courseRepo;
    private final LectureRepository lectureRepo;
    private final GCalendarService gCalendarService;

    CourseService(
            final CourseRepository courseRepo
            , final LectureRepository lectureRepo
            , final GCalendarService gCalendarService)
    {
        this.courseRepo = courseRepo;
        this.lectureRepo = lectureRepo;
        this.gCalendarService = gCalendarService;
    }

    void addCourse() throws Exception
    {
        var email1 = "example@example.com";

        var emails1 = Set.of(email1);

        var lesson1 = new Lecture(LocalDate.now(), LocalTime.of(18, 0), 90);
        var lesson2 = new Lecture(LocalDate.now().plusDays(1), LocalTime.of(18, 0), 90);
        var lesson3 = new Lecture(LocalDate.now().plusDays(2), LocalTime.of(18, 0), 90);

        var lessons1 = Set.of(lesson1, lesson2, lesson3);

        var course1 = new Course("Jakiś kurs", "refNumber", Level.B1, emails1, lessons1);

        this.courseRepo.save(course1);

        for (Lecture lecture : lessons1)
           this.gCalendarService.createEvent("tytuł spotkania", "opis", lecture.getOffsetStartTime(), lecture.getOffsetEndTime(), course1.getEmails());

    }

    LectureReadModel addLectureToCourse(final LectureWriteModel toAdd)
    {
        var course = getCourseByRefNumber(toAdd.getCourseRefNumber());

        var lecture = this.lectureRepo.save(new Lecture(
                toAdd.getLectureDate()
                , toAdd.getStartTime()
                , toAdd.getDurationMinutes()
                , course));

        return new LectureReadModel(
                lecture.getCourse().getRefNumber()
                , lecture.getLectureDate()
                , lecture.getStartTime()
                , lecture.getDurationMinutes()
        );
    }

    private Course getCourseByRefNumber(final String refNumber)
    {
        return this.courseRepo.findByRefNumber(refNumber)
                .orElseThrow(() -> new NoSuchElementException("Course with given ref number not found!"));
    }

    List<CourseFullReadModel> getCoursesAsReadModel()
    {
        var courses = this.courseRepo.findAllAsReadModel();

        var refNumbers = courses
                .stream()
                .map(CourseReadModel::refNumber)
                .toList();

        var lectures = this.lectureRepo.findByCourseRefNumbers(refNumbers);

        var lecturesMap = lectures
                .stream()
                .collect(Collectors.groupingBy(
                        LectureReadModel::courseRefNumber
                        , Collectors.toSet()));

        return courses
                .stream()
                .map(c -> new CourseFullReadModel(
                    c.refNumber()
                    , c.name()
                    , c.level()
                    , c.emails()
                    , lecturesMap.getOrDefault(c.refNumber(), Set.of())
                ))
                .toList();
    }

    List<Course> getCourses()
    {
        return this.courseRepo.findAll();
    }
}
