package ru.tsipino.sbertech.btchandler.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tsipino.sbertech.btchandler.entity.Currency;
import ru.tsipino.sbertech.btchandler.entity.CurrencyParameters;
import ru.tsipino.sbertech.btchandler.repository.CurrencyRepository;
import ru.tsipino.sbertech.btchandler.repository.ParametersRepository;
import ru.tsipino.sbertech.btchandler.util.HTMLCurrencyParser;
import ru.tsipino.sbertech.btchandler.util.HTMLExtractor;

@AllArgsConstructor
@Component
public class CurrencyService implements ICurrencyService {

  private ParametersRepository parametersRepository;
  private CurrencyRepository currencyRepository;
  private HTMLCurrencyParser htmlCurrencyParser;
  private HTMLExtractor htmlExtractor;

  @Override
  public void updateCurrencies() {
    List<CurrencyParameters> parametersList = parametersRepository.findAll();
    parametersList.forEach(
        item -> {
          String html = htmlExtractor.getHTML(item.getUrl());
          Double value = htmlCurrencyParser.getCurrencyValue(html);
          currencyRepository.save(Currency.builder().value(value).parameters(item).build());
        });
  }
}
