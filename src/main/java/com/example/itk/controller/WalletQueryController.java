package com.example.itk.controller;

import com.example.itk.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallets")
public class WalletQueryController {

    private final WalletService service;

    public WalletQueryController(WalletService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getBalance(id));
    }
}
