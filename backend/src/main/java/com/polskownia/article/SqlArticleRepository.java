package com.polskownia.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlArticleRepository extends ArticleRepository, JpaRepository<Article, Integer>
{

}
