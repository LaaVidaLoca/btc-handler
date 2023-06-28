package ru.tsipino.sbertech.btchandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HTMLCurrencyParser {
    public static Double getCurrencyValue(String html) {
        Document doc = Jsoup.parse(html);
        Element element = doc.selectFirst("div.chart__exnode__price");
        return parseCurrency(element.text());
    }

    private static Double parseCurrency(String value) {
        return Double.parseDouble(value.replace(" ","").replace(",","."));
    }
}
