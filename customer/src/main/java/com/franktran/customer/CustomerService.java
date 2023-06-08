package com.franktran.customer;

import com.franktran.clients.fraud.FraudClient;
import com.franktran.clients.notification.NotificationClient;
import com.franktran.domain.FraudCheckResponse;
import com.franktran.domain.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
    CustomerRepository repository,
    FraudClient fraudClient,
    NotificationClient notificationClient) {
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
    NotificationRequest notificationRequest = new NotificationRequest(
        customer.getId(),
        customer.getEmail(),
        String.format("Hi %s, welcome to Spring microservices...",
            customer.getFirstName())
    );
    notificationClient.sendNotification(notificationRequest);
  }
}
