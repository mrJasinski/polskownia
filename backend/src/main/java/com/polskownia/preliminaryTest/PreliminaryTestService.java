package com.polskownia.preliminaryTest;

import com.polskownia.preliminaryTest.dto.AnswerReadModel;
import com.polskownia.preliminaryTest.dto.QuestionReadModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class PreliminaryTestService
{
    private final QuestionRepository questionRepo;

    PreliminaryTestService(final QuestionRepository questionRepo)
    {
        this.questionRepo = questionRepo;
    }

    Page<Question> getAllQuestions(final Pageable pageable)
    {
        return this.questionRepo.findAll(pageable);
    }

//    Page<QuestionReadModel> getAllQuestionsAsReadModel(final Pageable pageable)
//    {
//        return this.questionRepo.findAllAsReadModel(pageable);
//    }

    Page<QuestionReadModel> getAllQuestionsAsReadModel(final Pageable pageable)
    {
        return this.questionRepo.findAll(pageable)
                .map(q -> new QuestionReadModel(
                        q.getLevel()
                        , q.getQuestion()
                        , q.getExplanation()
                        , q.getAnswers()
                            .stream()
                            .map(a -> new AnswerReadModel(
                                    a.getAnswer(),
                                    a.isCorrect()))
                .collect(Collectors.toSet())));
    }
}
