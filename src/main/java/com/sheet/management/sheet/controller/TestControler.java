package com.sheet.management.sheet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestControler {

    @GetMapping("/")
    public ResponseEntity<String> displayHelloWorld() {
        return ResponseEntity.ok().body("Hello World");
    }
}
