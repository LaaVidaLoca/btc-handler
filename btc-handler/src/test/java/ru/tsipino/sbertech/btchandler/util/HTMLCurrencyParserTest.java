package ru.tsipino.sbertech.btchandler.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HTMLCurrencyParserTest {

  @InjectMocks private HTMLCurrencyParser htmlCurrencyParser;

  @Test
  void getCurrencyValue_exist_class() {
    String testHTML =
        "<HTML><body><div class=\"chart__exnode__price js-chart-exnode-price\">30 470,31</div></body></HTML>";

    Double value = htmlCurrencyParser.getCurrencyValue(testHTML);

    assertEquals(30470.31, value);
  }
}
