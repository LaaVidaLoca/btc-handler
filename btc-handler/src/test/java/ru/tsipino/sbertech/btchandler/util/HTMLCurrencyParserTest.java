package ru.tsipino.sbertech.btchandler.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HTMLCurrencyParserTest {

    @Mock private Document doc;
    @InjectMocks private HTMLCurrencyParser htmlCurrencyParser;

    @Test
    void getCurrencyValue() {
        String testHTML = "<HTML><body>18 135,34</body></HTML>";
        Document document = Jsoup.parse(testHTML);
        when(doc.selectFirst(any(String.class))).thenReturn(document.selectFirst("body"));

        Double value = htmlCurrencyParser.getCurrencyValue("<HTML><body>18 135,34</body></HTML>");

        verify(doc).selectFirst(any(String.class));
    }
}
