package com.example.itk.service;

import com.example.itk.dto.OperationType;
import com.example.itk.dto.WalletOperationRequest;
import com.example.itk.entity.Wallet;
import com.example.itk.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository repository;

    public WalletService(WalletRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void processOperation(WalletOperationRequest request) {

        Wallet wallet = repository.findByIdForUpdate(request.id())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (request.operationType() == OperationType.DEPOSIT) {
            wallet.setBalance(wallet.getBalance().add(request.amount()));
            return;
        }

        if (wallet.getBalance().compareTo(request.amount()) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        wallet.setBalance(wallet.getBalance().subtract(request.amount()));
    }

    public BigDecimal getBalance(UUID id) {
        return repository.findById(id)
                .map(Wallet::getBalance)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }
}
