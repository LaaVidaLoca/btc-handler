package ru.tsipino.sbertech.btchandler.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tsipino.sbertech.btchandler.entity.Currency;
import ru.tsipino.sbertech.btchandler.entity.CurrencyParameters;
import ru.tsipino.sbertech.btchandler.repository.CurrencyRepository;
import ru.tsipino.sbertech.btchandler.repository.ParametersRepository;
import ru.tsipino.sbertech.btchandler.util.HTMLCurrencyParser;
import ru.tsipino.sbertech.btchandler.util.HTMLExtractor;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

  @Mock private ParametersRepository parametersRepository;
  @Mock private CurrencyRepository currencyRepository;
  @Mock private HTMLCurrencyParser htmlCurrencyParser;
  @Mock private HTMLExtractor htmlExtractor;
  @InjectMocks private CurrencyService currencyService;

  @Test
  void updateCurrencies() {
    final String type = "someType";
    final CurrencyParameters currencyParameters = new CurrencyParameters(1L, type, "url");
    final Double currencyValue = 30.22;

    when(parametersRepository.findAll()).thenReturn(List.of(currencyParameters));
    when(htmlCurrencyParser.getCurrencyValue(any())).thenReturn(currencyValue);

    currencyService.updateCurrencies();

    verify(parametersRepository).findAll();
    verify(currencyRepository)
        .save(Currency.builder().value(currencyValue).parameters(currencyParameters).build());
  }
}
