package com.polskownia.shop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ShopController
{
    @GetMapping("/shop")
    ResponseEntity<?> getShop()
    {
        return ResponseEntity.ok("shop!");
    }
}
