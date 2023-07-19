package ru.tsipino.sbertech.btchandler.service;

import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tsipino.sbertech.btchandler.entity.Currency;
import ru.tsipino.sbertech.btchandler.entity.CurrencyParameters;
import ru.tsipino.sbertech.btchandler.entity.Subscription;
import ru.tsipino.sbertech.btchandler.repository.CurrencyRepository;
import ru.tsipino.sbertech.btchandler.repository.ParametersRepository;
import ru.tsipino.sbertech.btchandler.repository.SubscriptionRepository;

@Component
@AllArgsConstructor
public class CurrencyRequestService {
  private CurrencyRepository currencyRepository;
  private ParametersRepository parametersRepository;
  private SubscriptionRepository subscriptionRepository;

  private static final int NUMBER_LAST_REQUEST_ROWS = 10;

  private List<Currency> cropCurrencyList(List<Currency> currencyList) {

    return currencyList.subList(0, Math.min(currencyList.size(), NUMBER_LAST_REQUEST_ROWS));
  }

  private List<CurrencyParameters> getParametersList(Long chatId) {
    List<Subscription> subscriptionList = subscriptionRepository.findAllByChatId(chatId);
    return subscriptionList.stream().map(Subscription::getParameters).toList();
  }

  public String getLastCurrencyList(Long chatId) {

    StringBuilder sb = new StringBuilder();
    getParametersList(chatId)
        .forEach(
            parameters -> {
              sb.append("Изменения курса ").append(parameters.getType()).append(":").append("\n");
              cropCurrencyList(currencyRepository.findAllByParameters(parameters))
                  .forEach(sb::append);
            });
    return sb.toString();
  }

  public boolean isEmptySubscriptions(Long chatId) {
    return subscriptionRepository.findAllByChatId(chatId).isEmpty();
  }

  public String getMaxCurrency(Long chatId) {
    StringBuilder sb = new StringBuilder();
    getParametersList(chatId)
        .forEach(
            parameters -> {
              sb.append("Максимальное значание ")
                  .append(parameters.getType())
                  .append(":")
                  .append("\n");
              Currency currency =
                  (currencyRepository.findAllByParameters(parameters))
                      .stream().max(Comparator.comparingDouble(Currency::getValue)).get();
              sb.append(currency);
            });
    sb.toString();
    return sb.toString();
  }

  public String getMinCurrency(Long chatId) {
    StringBuilder sb = new StringBuilder();
    getParametersList(chatId)
        .forEach(
            parameters -> {
              sb.append("Минимальное значение ")
                  .append(parameters.getType())
                  .append(":")
                  .append("\n");
              Currency currency =
                  (currencyRepository.findAllByParameters(parameters))
                      .stream().min(Comparator.comparingDouble(Currency::getValue)).get();
              sb.append(currency);
            });
    sb.toString();
    return sb.toString();
  }
}
