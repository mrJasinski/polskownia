package com.polskownia.article;

import jakarta.persistence.*;

import java.text.Normalizer;
import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
class Article
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String articleLink;
    private String articleCycle;
    private LocalDateTime publishDate;
    private String author;
    private String summary;
    private String articleText;

    Article()
    {
    }

    Article(
            final String title
            , final String articleCycle
            , final LocalDateTime publishDate
            , final String author
            , final String summary
            , final String articleText
            , final String logoReference)
    {
        this.title = title;
        this.articleLink = covertTitleToLink(title);
        this.articleCycle = articleCycle;
        this.publishDate = publishDate;
        this.author = author;
        this.summary = summary;
        this.articleText = articleText;
    }

    String covertTitleToLink(final String title)
    {
        if (title == null)
//            TODO czy tu raczej powinien być błąd?
            return null;

        var text = title.replaceAll(" ", "-");

        var normalized = Normalizer.normalize(text, Normalizer.Form.NFD);

        var result = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

//        ponieważ normalizer nie zawsze poprawnie usuwa ł/Ł
        result = result.replace("ł", "l").replace("Ł", "L");

        return result.toLowerCase();
    }
}
