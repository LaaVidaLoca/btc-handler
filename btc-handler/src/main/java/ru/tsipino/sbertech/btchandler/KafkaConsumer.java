package ru.tsipino.sbertech.btchandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.tsipino.sbertech.btchandler.controller.MsgController;
import ru.tsipino.sbertech.btchandler.dto.MessageDTO;
import ru.tsipino.sbertech.btchandler.service.CurrencyRequestService;

@RequiredArgsConstructor
@Component
@EnableKafka
public class KafkaConsumer {

  private final MsgController controller;
  private final CurrencyRequestService service;

  @KafkaListener(topics = "request")
  public void msgListener(String msg) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    MessageDTO messageDTO = mapper.readValue(msg, MessageDTO.class);
    String responseMsgText;
    if (service.isEmptySubscriptions(messageDTO.getChatId())) {
      responseMsgText = "У вас нет ни одной подписки";
    } else {
        responseMsgText = switch (messageDTO.getCommand()) {
        case "/last" -> service.getLastCurrencyList(messageDTO.getChatId());
        case "/max" -> service.getMaxCurrency(messageDTO.getChatId());
        case "/min" -> service.getMinCurrency(messageDTO.getChatId());
        default -> "";
      };
    }
    if (!responseMsgText.isEmpty()) {
      messageDTO.setCommand(responseMsgText);
      controller.sendOrder(messageDTO.getChatId(), messageDTO);
    }
  }
}
