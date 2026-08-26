package com.polskownia.shop;

import com.polskownia.shop.dto.ShopItemWriteModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ShopController
{
    @GetMapping("/shop")
    ResponseEntity<?> getShop()
    {
        return ResponseEntity.ok("shop!");
    }

    @PostMapping
    ResponseEntity<?> addShopItem(@RequestBody ShopItemWriteModel toSave)
    {
        return ResponseEntity.ok("shop item added!");
    }
}
