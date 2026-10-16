package com.polskownia.preliminaryTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface QuestionRepository
{
    Question save(Question toSave);

    Page<Question> findAll(Pageable pageable);
}
