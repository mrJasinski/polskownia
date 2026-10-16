package com.polskownia.contact;

import com.polskownia.contact.dto.ContactFormDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
class ContactController
{
    @GetMapping("/sendForm")
    ResponseEntity<?> sendForm(@RequestBody ContactFormDTO form)
    {


//        TODO
        return ResponseEntity.ok("Form sent!");
    }
}
