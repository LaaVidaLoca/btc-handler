package ru.tsipino.sbertech.btchandler.util;

import java.io.IOException;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
@AllArgsConstructor
public class HTMLExtractor {
  private CloseableHttpClient client;
  private CloseableHttpResponse response;
  private HttpEntity entity;

  public String getHTML(String url) {
    client = HttpClients.createDefault();
    HttpGet get = new HttpGet(url);
    String html = null;
    try {
      response = client.execute(get);
      entity = response.getEntity();
      html = EntityUtils.toString(entity);
    } catch (IOException e) {
      log.error("Can't extract HTML");
    }
    return html;
  }
}
