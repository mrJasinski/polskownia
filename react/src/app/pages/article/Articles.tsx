import { useEffect, useState } from "react";
import type { ArticleShortReadModel } from "../../models/ArticleShortReadModel";
import { getArticles } from "../../services/ArticleService";
import type { Page } from "../../models/Page";

function Articles() 
{
  
    const [page, setPage] = useState<Page<ArticleShortReadModel> | null > (null);

    useEffect(() => {
        getArticles()
            .then(setPage)
            .catch(error => { console.error("ERROR:", error); });
    }, []);

    if (!page) {
        return <div>Ładowanie...</div>;
    }

    return (
        <div>
            <h1>Artykuły</h1>

            {page.content.map(article => (
                <div key={article.title}>

                    <h3>{article.title}</h3>
                    <p>{article.cycle}</p>
                    <p>{article.author}</p>
                    <p>{article.publishDate}</p>
                    <p>{article.summary}</p>
                    
                </div>
            ))}
        </div>
    );
}

export default Articles;