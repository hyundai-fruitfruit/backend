package com.hyundai.app.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/")
@RestController
public class AdminController {

    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Health Check OK!");
    }
}