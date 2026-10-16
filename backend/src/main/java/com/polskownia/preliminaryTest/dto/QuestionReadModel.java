package com.polskownia.preliminaryTest.dto;

import com.polskownia.utils.Level;
import java.util.Set;

public record QuestionReadModel(
        Level level
        , String question
        , String explanation
        , Set<AnswerReadModel> answers
)
{
}
