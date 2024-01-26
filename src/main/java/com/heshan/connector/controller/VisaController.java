package com.heshan.connector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heshan.connector.service.VisaService;

@RestController
@RequestMapping("/api")
public class VisaController {
    
    @Autowired
    private VisaService visaService;

    @GetMapping("/helloworld-status")
    public ResponseEntity<String> getHelloWorldStatus() {
        return visaService.callHelloWrold();
    }
}
