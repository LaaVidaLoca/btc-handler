package ru.tsipino.sbertech.btchandler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HTMLExtractorTest {

  @InjectMocks private HTMLExtractor extractor;

  @Test
  void getHTML_null_response() {
    final String url = "http://localhost:8080";

    String resultHtml = extractor.getHTML(url);

    assertNull(resultHtml);
  }

  @Test
  void getHTML_ok_response() {
    final String url = "https://google.com";

    String resultHtml = extractor.getHTML(url);

    assertNotNull(resultHtml);
  }
}
