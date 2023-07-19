package ru.tsipino.sbertech.btchandler.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HTMLCurrencyParser {

  public Double getCurrencyValue(String html) {
    if (html == null || html.isEmpty()) {
      return null;
    }
    Document doc = Jsoup.parse(html);
    Element element = doc.selectFirst("div.chart__exnode__price");
    assert element != null;
    return parseCurrency(element.text());
  }

  private Double parseCurrency(String value) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    return Double.parseDouble(value.replace(" ", "").replace(",", "."));
  }
}
