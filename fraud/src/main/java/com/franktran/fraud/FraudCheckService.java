package com.franktran.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckService(FraudCheckHistoryRepository repository) {
  public boolean isFraudulentCustomer(Integer customerId) {
    FraudCheckHistory fraudCheckHistory = FraudCheckHistory
        .builder()
        .customerId(customerId)
        .isFraudster(false)
        .createdAt(LocalDateTime.now())
        .build();
    repository.save(fraudCheckHistory);
    return false;
  }
}
