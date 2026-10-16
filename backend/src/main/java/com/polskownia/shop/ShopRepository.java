package com.polskownia.shop;

import com.polskownia.shop.dto.ShopItemReadModel;
import com.polskownia.shop.dto.ShopItemShortReadModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

interface ShopRepository
{
    Optional<ShopItemReadModel> findByRefNumber(String refNumber);

    ShopItem save(ShopItem toSave);

    boolean existsByTitle(String title1);

    Page<ShopItemShortReadModel> findAllAsShortReadModel(final Pageable pageable);

    Page<ShopItem> findAll(final Pageable pageable);
}
