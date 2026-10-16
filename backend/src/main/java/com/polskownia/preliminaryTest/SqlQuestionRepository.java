package com.polskownia.preliminaryTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface SqlQuestionRepository extends QuestionRepository, JpaRepository<Question, Integer>
{

}
