package com.polskownia.article.dto;

import java.time.LocalDateTime;

public record ArticleReadModel(
        String title
        , String link
        , LocalDateTime publishDate
        , String cycle
        , String author
        , String text)
{
}
