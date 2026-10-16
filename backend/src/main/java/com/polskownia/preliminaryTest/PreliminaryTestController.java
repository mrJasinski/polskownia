package com.polskownia.preliminaryTest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
class PreliminaryTestController
{
    private final PreliminaryTestService preliminaryTestService;

    PreliminaryTestController(final PreliminaryTestService preliminaryTestService)
    {
        this.preliminaryTestService = preliminaryTestService;
    }

    @GetMapping("/questions")
    ResponseEntity<?> getAllQuestionsAsReadModel(final Pageable pageable)
    {
        return ResponseEntity.ok(this.preliminaryTestService.getAllQuestionsAsReadModel(pageable));
    }
}
