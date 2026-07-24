package com.polskownia.shop;

import org.springframework.stereotype.Repository;

@Repository
interface SqlShopRepository extends ShopRepository, JpaRepository<>
{
}
