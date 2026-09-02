package com.polskownia.shop;

import com.polskownia.shop.dto.ShopItemWriteModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/shop")
class ShopController
{
    private final ShopService shopService;

    ShopController(final ShopService shopService)
    {
        this.shopService = shopService;
    }

    @GetMapping
    ResponseEntity<?> getShop()
    {
        return ResponseEntity.ok("shop!");
    }

    @GetMapping("/items")
    ResponseEntity<?> getShopItems(Pageable pageable)
    {
        return ResponseEntity.ok(this.shopService.getShopItemsAsShortReadModel(pageable));
    }

    @GetMapping("/{refNumber}")
    ResponseEntity<?> getShopItemByRefNumer(@PathVariable String refNumber)
    {
        return ResponseEntity.ok(this.shopService.getShopItemByRefNumberAsReadModel(refNumber));
    }

    @PostMapping("/add")
    ResponseEntity<?> addShopItem(@RequestBody ShopItemWriteModel toSave)
    {
        return ResponseEntity.ok("shop item added!");
    }


}
