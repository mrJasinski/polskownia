package com.polskownia.shop;

import com.polskownia.shop.dto.ShopItemReadModel;
import com.polskownia.shop.dto.ShopItemShortReadModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
class ShopService
{
    private final ShopRepository shopRepo;

    ShopService(final ShopRepository shopRepo)
    {
        this.shopRepo = shopRepo;
    }

    ShopItemReadModel getShopItemByRefNumberAsReadModel(final String refNumber)
    {
        return this.shopRepo.findByRefNumber(refNumber)
                .orElseThrow(() -> new NoSuchElementException("Shop item with given ref number not found!"));
    }

    Page<ShopItemShortReadModel> getShopItemsAsShortReadModel(final Pageable pageable)
    {
        return this.shopRepo.findAllAsShortReadModel(pageable);
    }


}
