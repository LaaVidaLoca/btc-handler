package ru.tsipino.sbertech.btchandler.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HTMLExtractorTest {

    @Mock private CloseableHttpClient client;

    @Mock private CloseableHttpResponse response;

    @Mock private HttpEntity entity;

    @InjectMocks private HTMLExtractor extractor;

    @Test
    void getHTML() throws IOException {
        final String url = "https://example.com";
        when(client.execute(any(HttpGet.class))).thenReturn(response);
        when(response.getEntity()).thenReturn(entity);
        when(EntityUtils.toString(entity)).thenReturn("some HTML");

        String resultHtml = extractor.getHTML(url);

        verify(client).execute(any(HttpGet.class));
        verify(response).getEntity();
    }
}
