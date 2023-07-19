package ru.tsipino.sbertech.btchandler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsipino.sbertech.btchandler.dto.MessageDTO;

@Component
@RestController
@RequestMapping("response")
public class MsgController {
  public MsgController(KafkaTemplate<Long, MessageDTO> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Autowired private KafkaTemplate<Long, MessageDTO> kafkaTemplate;

  @PostMapping
  public void sendOrder(Long msgId, MessageDTO msg) {
    kafkaTemplate.send("response", msgId, msg);
  }
}
