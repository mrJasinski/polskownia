package com.polskownia.course;

import com.polskownia.course.dto.LectureWriteModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
class CourseController
{
    private final CourseService courseService;

    CourseController(final CourseService courseService)
    {
        this.courseService = courseService;
    }

    @GetMapping
    ResponseEntity<?> getCourses()
    {
        return ResponseEntity.ok(this.courseService.getCoursesAsReadModel());
    }

    @GetMapping("/add")
    ResponseEntity<?> addCourse() throws Exception
    {
//        TODO testowy więc get zamiast post
        this.courseService.addCourse();

        return ResponseEntity.ok("Course added!");
    }

    @PostMapping("/addLecture")
    ResponseEntity<?> addLectureToCourse(@RequestBody LectureWriteModel toAdd)
    {
        return ResponseEntity.ok(this.courseService.addLectureToCourse(toAdd));
    }
}
