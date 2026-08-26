package com.polskownia.article;

import com.polskownia.article.dto.ArticleWriteModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/articles")
class ArticleController
{
    private final ArticleService articleService;

    ArticleController(final ArticleService articleService)
    {
        this.articleService = articleService;
    }

    @GetMapping
    ResponseEntity<?> getArticles(Pageable pageable)
    {
        return ResponseEntity.ok(this.articleService.getAllPublishedArticlesAsShortReadModel(pageable));
    }

    @GetMapping("/{link}")
    ResponseEntity<?> getArticleByLink(@PathVariable("link") String link)
    {
        return ResponseEntity.ok(this.articleService.getArticleByLinkAsReadModel(link));
    }

    @GetMapping("/dashboard")
    ResponseEntity<?> getArticlesDashboard()
    {
        return ResponseEntity.ok(this.articleService.getDashboard());
    }

    @PostMapping("/add")
    ResponseEntity<?> addArticle(@RequestBody ArticleWriteModel toSave)
    {
        return ResponseEntity.ok(this.articleService.createArticle(toSave));
    }

}
