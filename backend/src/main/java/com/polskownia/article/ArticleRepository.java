package com.polskownia.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface ArticleRepository
{
    Article save(final Article toSave);

    boolean existsByArticleLink(final String link);

    Page<Article> findAllPublished(Pageable pageable, LocalDateTime timestamp);

    List<Article> findAll();

    Optional<Article> findByArticleLink(String link);
}
