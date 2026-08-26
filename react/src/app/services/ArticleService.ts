import type { ArticleShortReadModel } from "../models/ArticleShortReadModel";
import type { Page } from "../models/Page";

export async function getArticles() : Promise<Page<ArticleShortReadModel>>
{
    const response = await fetch("http://localhost:8080/articles");

    if (!response.ok) 
        throw new Error(`HTTP error: ${response.status}`);

    return response.json();
}