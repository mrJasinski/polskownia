package com.polskownia.shop;

import com.polskownia.shop.dto.ShopItemShortReadModel;
import com.polskownia.utils.Category;
import com.polskownia.utils.Level;
import jakarta.persistence.*;

@Entity
@Table(name = "shop_items")
class ShopItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String refNumber;
    private String title;
    private double price;
    private String shortDescription;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Level level;

    ShopItem()
    {
    }

    ShopItem(final String title, final double price, final String shortDescription, final String description, final Category category, final Level level)
    {
        this.refNumber = generateRefNumber(category.getAcronym(), level.name(), title);
        this.title = title;
        this.price = price;
        this.shortDescription = shortDescription;
        this.description = description;
        this.category = category;
        this.level = level;
    }

    String generateRefNumber(final String acronym , final String level, final String title)
    {
        return acronym + "_" + level + "_" + title.hashCode();
    }

    public int getId()
    {
        return this.id;
    }

    public String getRefNumber()
    {
        return this.refNumber;
    }

    public String getTitle()
    {
        return this.title;
    }

    public double getPrice()
    {
        return this.price;
    }

    public String getShortDescription()
    {
        return this.shortDescription;
    }

    public String getDescription()
    {
        return this.description;
    }

    public Category getCategory()
    {
        return this.category;
    }

    public Level getLevel()
    {
        return this.level;
    }
}
