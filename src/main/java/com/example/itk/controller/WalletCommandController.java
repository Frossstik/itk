package com.example.itk.controller;

import com.example.itk.dto.WalletOperationRequest;
import com.example.itk.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletCommandController {

    private final WalletService service;

    public WalletCommandController(WalletService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> operate(@RequestBody WalletOperationRequest request) {
        service.processOperation(request);
        return ResponseEntity.ok().build();
    }
}
