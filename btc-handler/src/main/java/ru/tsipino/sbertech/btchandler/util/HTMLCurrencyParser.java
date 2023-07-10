package ru.tsipino.sbertech.btchandler.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class HTMLCurrencyParser {

  private Document doc;

  public Double getCurrencyValue(String html) {
    if (html == null || html.isEmpty()) {
      return null;
    }
    doc = Jsoup.parse(html);
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
