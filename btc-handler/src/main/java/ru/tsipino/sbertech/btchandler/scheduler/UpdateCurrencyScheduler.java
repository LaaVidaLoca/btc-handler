package ru.tsipino.sbertech.btchandler.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j // лог, лучше ставить везде
@RequiredArgsConstructor
public class UpdateCurrencyScheduler {

  private static final String UPDATE_CURRENCY_INTERVAL = "${update-currency.period}"; // берет переменную из application.properties

  @Scheduled(fixedRateString = UPDATE_CURRENCY_INTERVAL) // задаем интервал
  public void update() {
    log.info("Hello World!"); // каждые 5 сек будем выводить
  }
}
