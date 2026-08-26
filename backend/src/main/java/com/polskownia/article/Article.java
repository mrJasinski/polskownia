package com.polskownia.article;

import com.polskownia.article.dto.ArticleReadModel;
import com.polskownia.article.dto.ArticleShortReadModel;
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
            , final String articleText)
    {
        this.title = title;
        this.articleLink = covertTitleToLink(title);
        this.articleCycle = articleCycle;
        this.publishDate = publishDate; //TODO wystarczy data i ew godz:min
        this.author = author;
        this.summary = summary;
        this.articleText = articleText;
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
        this(title, articleCycle, publishDate, author, summary, articleText);
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

    ArticleShortReadModel toShortReadModel()
    {
        return new ArticleShortReadModel(
                this.publishDate
                , this.articleCycle
                , this.title
                , this.articleLink
                , this.author
                , this.summary
        );
    }

    ArticleReadModel toReadModel()
    {
        return new ArticleReadModel(
                this.title
                , this.articleLink
                , this.publishDate
                , this.articleCycle
                , this.author
                , this.articleText
        );
    }

}
