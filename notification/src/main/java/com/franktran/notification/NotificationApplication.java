package com.franktran.notification;

import com.franktran.amqp.RabbitMQProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
    scanBasePackages = {"com.franktran.amqp", "com.franktran.notification"}
)
@EnableEurekaClient
public class NotificationApplication {
  public static void main(String[] args) {
    SpringApplication.run(NotificationApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(RabbitMQProducer producer, NotificationConfig config) {
    return args -> {
      producer.publish(config.getInternalExchange(), config.getRoutingKey(), new Person("Bob", 20));
    };
  }

  record Person(String name, int age) {}
}
