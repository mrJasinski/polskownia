package com.polskownia.shop.dto;

import com.polskownia.utils.Category;
import com.polskownia.utils.Level;

public record ShopItemShortReadModel(
        String refNumber
        , String title
        , double price
        , String shortDescription
        , Category category
        , Level level

//        określenie dostępności?
)
{
}