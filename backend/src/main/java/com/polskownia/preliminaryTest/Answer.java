package com.polskownia.preliminaryTest;

import com.polskownia.preliminaryTest.dto.AnswerReadModel;
import jakarta.persistence.*;

@Entity
@Table(name = "answers")
class Answer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String answer;
    private boolean isCorrect;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    Answer()
    {
    }

    Answer(final String answer, final boolean isCorrect)
    {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    Answer(final String answer, final boolean isCorrect, final Question question)
    {
        this(answer, isCorrect);
        this.question = question;
    }

    AnswerReadModel toReadModel()
    {
        return new AnswerReadModel(
                this.answer
                , this.isCorrect
        );
    }

    public int getId()
    {
        return this.id;
    }

    public String getAnswer()
    {
        return this.answer;
    }

    public boolean isCorrect()
    {
        return this.isCorrect;
    }

    public Question getQuestion()
    {
        return this.question;
    }

    void setQuestion(final Question question)
    {
        this.question = question;
    }
}
