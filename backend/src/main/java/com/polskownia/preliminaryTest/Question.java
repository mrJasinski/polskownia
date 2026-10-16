package com.polskownia.preliminaryTest;

import com.polskownia.preliminaryTest.dto.QuestionReadModel;
import com.polskownia.utils.Level;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
class Question
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Level level;
    private String question;
    private String explanation;
//    TODO powtórka z cascade type - co oznaczają itd
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers = new HashSet<>();

    Question()
    {
    }

    Question(final Level level, final String question, final String explanation)
    {
        this.level = level;
        this.question = question;
        this.explanation = explanation;
    }

    Question(final Level level, final String question, final Set<Answer> answers, final String explanation)
    {
        this(level, question, explanation);

        addAnswers(answers);
    }

//    QuestionReadModel toReadModel()
//    {
//        return new QuestionReadModel();
//    }

    void addAnswers(final Set<Answer> answers)
    {
        this.answers = answers;

        this.answers.forEach(a -> a.setQuestion(this));
    }

    public int getId()
    {
        return this.id;
    }

    public Level getLevel()
    {
        return this.level;
    }

    public String getQuestion()
    {
        return this.question;
    }

    public Set<Answer> getAnswers()
    {
        return this.answers;
    }

    public String getExplanation()
    {
        return this.explanation;
    }
}
