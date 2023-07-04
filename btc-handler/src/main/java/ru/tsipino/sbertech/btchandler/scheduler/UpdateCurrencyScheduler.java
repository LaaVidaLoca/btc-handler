package ru.tsipino.sbertech.btchandler.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tsipino.sbertech.btchandler.service.CurrencyService;

@Slf4j
@RequiredArgsConstructor
@Component
public class UpdateCurrencyScheduler {

  private static final String UPDATE_CURRENCY_INTERVAL = "${update-currency.period}";
  private final CurrencyService currencyService;

  @Scheduled(fixedRateString = UPDATE_CURRENCY_INTERVAL)
  public void update() {
    currencyService.updateCurrencies();
  }
}
