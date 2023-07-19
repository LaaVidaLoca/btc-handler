package ru.tsipino.sbertech.btchandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableKafka
public class BtcHandlerApplication {

  public static void main(String[] args) {
    SpringApplication.run(BtcHandlerApplication.class, args);
  }
}
