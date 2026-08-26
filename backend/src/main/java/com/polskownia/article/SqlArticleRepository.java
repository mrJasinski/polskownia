package com.polskownia.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
interface SqlArticleRepository extends ArticleRepository, JpaRepository<Article, Integer>
{
    @Override
    @Query(value = "SELECT * FROM articles WHERE publish_date < :timestamp", nativeQuery = true)
    Page<Article> findAllPublished(Pageable pageable, LocalDateTime timestamp);
}
