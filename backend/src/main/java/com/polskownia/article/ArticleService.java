package com.polskownia.article;

import com.polskownia.article.dto.ArticleReadModel;
import com.polskownia.article.dto.ArticleShortReadModel;

import com.polskownia.article.dto.ArticleWriteModel;
import com.polskownia.article.dto.ArticlesDashboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
class ArticleService
{
    private final ArticleRepository articleRepo;

    ArticleService(final ArticleRepository articleRepo)
    {
        this.articleRepo = articleRepo;
    }

    Page<Article> getAllPublishedArticles(org.springframework.data.domain.Pageable pageable)
    {
        var timestamp = LocalDateTime.now();

        return this.articleRepo.findAllPublished(pageable, timestamp);
    }

    Page<ArticleShortReadModel> getAllPublishedArticlesAsShortReadModel(Pageable pageable)
    {
        return getAllPublishedArticles(pageable)
                .map(Article::toShortReadModel);
    }

    Article getArticleByLink(final String link)
    {
        return this.articleRepo.findByArticleLink(link)
                .orElseThrow(() -> new NoSuchElementException("Article with given link title not found!"));
    }

    ArticleReadModel getArticleByLinkAsReadModel(final String link)
    {
        return getArticleByLink(link).toReadModel();
    }

    ArticlesDashboard getDashboard()
    {
//        TODO - co należy zawrzeć w takim dashboardzie?+
        return new ArticlesDashboard();
    }

    Article createArticle(final ArticleWriteModel toSave)
    {
        return this.articleRepo.save(new Article(
                toSave.getTitle()
                , toSave.getArticleCycle()
                , toSave.getPublishDate()
                , toSave.getAuthor()
                , toSave.getSummary()
                , toSave.getArticleText()
        ));
    }
}
