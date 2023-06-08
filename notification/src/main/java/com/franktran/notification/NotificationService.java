package com.franktran.notification;

import com.franktran.domain.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository repository) {
  public void send(NotificationRequest notificationRequest) {
    repository.save(
        Notification.builder()
            .toCustomerId(notificationRequest.toCustomerId())
            .toCustomerEmail(notificationRequest.toCustomerName())
            .sender("Amigoscode")
            .message(notificationRequest.message())
            .sentAt(LocalDateTime.now())
            .build()
    );
  }
}
