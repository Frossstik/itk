package com.example.itk.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletOperationRequest(UUID id,
                                     OperationType operationType,
                                     BigDecimal amount) {
}
