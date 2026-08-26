package com.polskownia.article.dto;

import java.time.LocalDateTime;

public class ArticleWriteModel
{
    private String title;
    private String articleCycle;
    private LocalDateTime publishDate;
    private String author;
    private String summary;
    private String articleText;

    ArticleWriteModel()
    {
    }

    ArticleWriteModel(
            final String title
            , final String articleCycle
            , final LocalDateTime publishDate
            , final String author
            , final String summary
            , final String articleText)
    {
        this.title = title;
        this.articleCycle = articleCycle;
        this.publishDate = publishDate;
        this.author = author;
        this.summary = summary;
        this.articleText = articleText;
    }

    public String getTitle()
    {
        return title;
    }

    public String getArticleCycle()
    {
        return articleCycle;
    }

    public LocalDateTime getPublishDate()
    {
        return publishDate;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getSummary()
    {
        return summary;
    }

    public String getArticleText()
    {
        return articleText;
    }
}
