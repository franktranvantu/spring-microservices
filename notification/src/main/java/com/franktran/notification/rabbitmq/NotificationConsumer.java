package com.franktran.notification.rabbitmq;

import com.franktran.domain.NotificationRequest;
import com.franktran.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
  private final NotificationService service;

  @RabbitListener(queues = "${rabbitmq.queues.notification}")
  public void consume(NotificationRequest request) {
    log.info("Consumed message {} from queue", request);
    service.send(request);
  }
}
