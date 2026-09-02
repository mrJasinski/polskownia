package com.polskownia.shop.dto;

import com.polskownia.utils.Category;
import com.polskownia.utils.Level;

public record ShopItemReadModel(
        String refNumber
        , String title
        , double price
        , String description
        , Category category
        , Level level
)
{
}
