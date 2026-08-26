package com.polskownia.article;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class ArticleInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final ArticleRepository articleRepo;

    ArticleInitializer(final ArticleRepository articleRepo)
    {
        this.articleRepo = articleRepo;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        var link1 = "dzien-z-zycia-lektora";
        if (!this.articleRepo.existsByArticleLink(link1))
            this.articleRepo.save(new Article(
                    "Dzień z życia lektora"
                    , "Życie lektora"
                    , LocalDateTime.now()
                    , "Wiolotka"
                    , "Lorem ipsum i tak dalej"
                    , "artykul1.doc"
                    , "logo1.png"
            ));

        var link2 = "gniezno-pierwsza-stolica-polski";
        if (!this.articleRepo.existsByArticleLink(link2))
            this.articleRepo.save(new Article(
                    "Gniezno pierwsza stolica Polski"
                    , "O Polsce"
                    , LocalDateTime.now()
                    , "Wiolotka"
                    , "Lorem ipsum i tak dalej"
                    , "artykul2.doc"
                    , "logo2.png"
            ));

        var link3 = "wizyta-w-teatrze-narodowym";
        if (!this.articleRepo.existsByArticleLink(link3))
            this.articleRepo.save(new Article(
                    "Wizyta w Teatrze Narodowym"
                    , "Kultura"
                    , LocalDateTime.now()
                    , "Wiolotka"
                    , "Lorem ipsum i tak dalej"
                    , "artykul3.doc"
                    , "logo3.png"
            ));
    }
}
