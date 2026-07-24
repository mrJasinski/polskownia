package com.polskownia.article.dto;

import java.time.LocalDateTime;

record ArticleShortReadModel(
        LocalDateTime publishDate
        , String cycle
        , String title
        , String link
        , String author
        , String summary)
{
}
