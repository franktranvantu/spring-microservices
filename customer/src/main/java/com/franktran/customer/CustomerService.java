package com.franktran.customer;

import com.franktran.domain.FraudCheckResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository repository, RestTemplate restTemplate) {
  public void registerCustomer(CustomerRegistrationRequest request) {
    Customer customer = Customer
        .builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .email(request.email())
        .build();
    // TODO
    // Check if email is valid
    // Check if email is taken
    // Store customer in DB
    repository.saveAndFlush(customer);
    FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
        "http://FRAUD/api/v1/fraud-check/{customerId}",
        FraudCheckResponse.class,
        customer.getId()
    );
    if (fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("Customer is fraudster");
    }
  }
}
