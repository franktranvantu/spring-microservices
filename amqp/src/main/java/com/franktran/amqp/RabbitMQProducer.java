package com.franktran.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducer {
  private final AmqpTemplate amqpTemplate;

  public void publish(String exchange, String routingKey, Object message) {
    log.info("Publishing to {} using routing key {}. Message: {}", exchange, routingKey, message);
    amqpTemplate.convertAndSend(exchange, routingKey, message);
    log.info("Published to {} using routing key {}. Message: {}", exchange, routingKey, message);
  }
}
