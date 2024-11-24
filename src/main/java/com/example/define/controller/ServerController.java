package com.example.define.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServerController {

    // GET
    @GetMapping("/healthy")
    public ResponseEntity<String> healthCheck() {
        // 정상적으로 작동하면 200 OK 반환
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}