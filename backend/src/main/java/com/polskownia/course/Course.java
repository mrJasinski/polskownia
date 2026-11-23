package com.polskownia.course;

import com.polskownia.utils.Level;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "courses")
class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String refNumber;
    private String name;
    @Enumerated(EnumType.STRING)
    private Level level;
    private Set<String> emails;
    @OneToMany(mappedBy = "course")
    private Set<Lecture> lectures;

    Course()
    {
    }

    Course(final String name, final Level level, final Set<String> emails, final Set<Lecture> lectures)
    {
        this.name = name;
        this.level = level;
        this.emails = emails;
        this.lectures = lectures;
    }

    Course(final String refNumber, final String name, final Level level, final Set<String> emails, final Set<Lecture> lectures)
    {
        this(name, level, emails, lectures);
        this.refNumber = refNumber;
    }

    void addLecture(final Lecture toAdd)
    {
        this.lectures.add(toAdd);
        toAdd.setCourse(this);
    }

    public int getId()
    {
        return this.id;
    }

    public String getRefNumber()
    {
        return this.refNumber;
    }

    public String getName()
    {
        return this.name;
    }

    public Level getLevel()
    {
        return this.level;
    }

    public Set<String> getEmails()
    {
        return this.emails;
    }

    public Set<Lecture> getLectures()
    {
        return this.lectures;
    }
}
