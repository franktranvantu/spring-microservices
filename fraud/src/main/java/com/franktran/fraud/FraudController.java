package com.franktran.fraud;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudController(FraudCheckService service) {
  @PostMapping("{customerId}")
  public FraudResponse isFraudster(@PathVariable Integer customerId) {
    boolean isFraudulentCustomer = service.isFraudulentCustomer(customerId);
    return new FraudResponse(isFraudulentCustomer);
  }
}
