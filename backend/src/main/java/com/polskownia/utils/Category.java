package com.polskownia.utils;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.NoSuchElementException;

public enum Category
{
    COURSE_INDIVIDUAL("Kurs indywidualny", "CI")
    , COURSE_GROUP("Kurs grupowy", "CG")
    , EXAM_SIMULATION("Symulacja egzaminu", "ES")
    , BOOK("Książka", "BK")
    , EBOOK("Ebook", "EB")
    , EXERCISE_SHEET("Arkusz ćwiczeń", "EX");

    private final String name;
    private final String acronym;

    Category(final String name, final String acronym)
    {
        this.name = name;
        this.acronym = acronym;
    }

    public static Category getByName(String name)
    {
        for(Category c : values())
            if (c.getName().equals(name))
                return c;

        throw new NoSuchElementException("Category with given name not found!");
    }

    @JsonValue
    public String getName()
    {
        return this.name;
    }

    public String getAcronym()
    {
        return this.acronym;
    }
}
