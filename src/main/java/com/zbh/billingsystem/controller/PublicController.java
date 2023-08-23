package com.zbh.billingsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        log.info("Ping method is called");
        return new ResponseEntity<>("Successfully ping the public url.", HttpStatus.OK);
    }
}
