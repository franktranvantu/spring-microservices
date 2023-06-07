package com.franktran.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository repository) {
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
    repository.save(customer);
  }
}
