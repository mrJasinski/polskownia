package com.polskownia.preliminaryTest;

import com.polskownia.utils.Level;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
class QuestionInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final QuestionRepository questionRepo;

    QuestionInitializer(final QuestionRepository questionRepo)
    {
        this.questionRepo = questionRepo;
    }


    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        var a1 = new Answer("lekarzy", false);
        var a2 = new Answer("lekarze", true);
        var a3 = new Answer("lekarzów", false);
        var a4 = new Answer("lekarzowie", false);

        var s1 = Set.of(a1, a2, a3, a4);

        var l1 = Level.A2;
        var q1 = "jak powiesz lekarz w liczbie mnogiej?";
        var e1 = "lorem ipsum i tak ma być";

        var question1 = new Question(l1, q1, s1, e1);

        this.questionRepo.save(question1);
    }
}