package com.example.itk;

import com.example.itk.dto.OperationType;
import com.example.itk.dto.WalletOperationRequest;
import com.example.itk.entity.Wallet;
import com.example.itk.repository.WalletRepository;
import com.example.itk.service.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletService walletService;

    @Test
    void shouldIncreaseBalance() {
        UUID id = UUID.randomUUID();
        BigDecimal balance = new BigDecimal("100");

        Wallet wallet = new Wallet();

        wallet.setId(id);
        wallet.setBalance(balance);

        when(walletRepository.findById(id)).thenReturn(Optional.of(wallet));

        WalletOperationRequest request =
                new WalletOperationRequest(id, OperationType.DEPOSIT, new BigDecimal("50"));

        walletService.processOperation(request);

        assertEquals(new BigDecimal("150"), wallet.getBalance());
    }
}
