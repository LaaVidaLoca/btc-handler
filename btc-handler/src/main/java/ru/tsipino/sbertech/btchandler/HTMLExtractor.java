package ru.tsipino.sbertech.btchandler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HTMLExtractor  {
    public static String getHTML(String url) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        String html = null;
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            html = EntityUtils.toString(entity);
        } catch (IOException e) {
            System.out.println();
        }
        return html;
    }
}
