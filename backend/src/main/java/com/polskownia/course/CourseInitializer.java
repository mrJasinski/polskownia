package com.polskownia.course;

import com.polskownia.google.gCalendar.GCalendarService;
import com.polskownia.mail.MailService;
import com.polskownia.utils.Level;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Component
class CourseInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final CourseRepository courseRepo;

    CourseInitializer(final CourseRepository courseRepo)
    {
        this.courseRepo = courseRepo;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        var email1 = "example@example.com";

        var emails1 = Set.of(email1);

        var lesson1 = new Lecture(LocalDate.now(), LocalTime.of(18, 0), 90);
        var lesson2 = new Lecture(LocalDate.now().plusDays(1), LocalTime.of(18, 0), 90);
        var lesson3 = new Lecture(LocalDate.now().plusDays(2), LocalTime.of(18, 0), 90);

        var lessons1 = Set.of(lesson1, lesson2, lesson3);

        var course1 = new Course("refNumber", "Jakiś kurs", Level.B1, emails1, lessons1);

        this.courseRepo.save(course1);
    }
}
