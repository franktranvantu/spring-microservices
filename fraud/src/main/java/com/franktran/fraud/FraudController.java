package com.franktran.fraud;

import com.franktran.domain.FraudCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
public record FraudController(FraudCheckService service) {
  @GetMapping("{customerId}")
  public FraudCheckResponse isFraudster(@PathVariable Integer customerId) {
    log.info("Fraud check request for customer {}", customerId);
    boolean isFraudulentCustomer = service.isFraudulentCustomer(customerId);
    return new FraudCheckResponse(isFraudulentCustomer);
  }
}
