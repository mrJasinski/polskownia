package com.polskownia.shop;

import com.polskownia.shop.dto.ShopItemShortReadModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface SqlShopRepository extends ShopRepository, JpaRepository<ShopItem, Integer>
{
    @Override
//    @Query(value = "SELECT * FROM shop_items", nativeQuery = true)
    @Query(value = "SELECT s.refNumber, s.title, s.price, s.shortDescription, s.category, s.level FROM ShopItem s", nativeQuery = false)
    Page<ShopItemShortReadModel> findAllAsShortReadModel(final Pageable pageable);
}
