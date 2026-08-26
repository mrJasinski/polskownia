package com.polskownia.shop;

import jakarta.persistence.*;

@Entity
@Table(name = "shop_items")
class ShopItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
