package com.franktran.notification;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class NotificationConfig {
  @Value("${rabbitmq.exchanges.internal}")
  private String internalExchange;
  @Value("${rabbitmq.queues.notification}")
  private String notificationQueue;
  @Value("${rabbitmq.routing-keys.internal-notification}")
  private String routingKey;

  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(internalExchange);
  }

  @Bean
  public Binding binding() {
    return BindingBuilder
        .bind(queue())
        .to(topicExchange())
        .with(routingKey);
  }

  @Bean
  public Queue queue() {
    return new Queue(notificationQueue);
  }
}
