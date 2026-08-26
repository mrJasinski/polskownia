package com.polskownia.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlShopRepository extends ShopRepository, JpaRepository<ShopItem, Integer>
{
}
