package com.franktran.customer;

import com.franktran.clients.fraud.FraudClient;
import com.franktran.domain.FraudCheckResponse;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
    CustomerRepository repository,
    FraudClient fraudClient) {
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
    FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
    if (fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("Customer is fraudster");
    }
  }
}
