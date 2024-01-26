package com.heshan.connector.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import com.heshan.connector.config.SSLConfig;

@ExtendWith(MockitoExtension.class)
public class HttpClientServiceTests {

    @Mock
    private SSLConfig sslConfig;

    @Mock
    private CloseableHttpClient httpClient;

    @InjectMocks
    private HttpClientService httpClientService;

    @Test
    public void testExecuteRequest_shouldReturnResponse() throws Exception {
        // Arrange
        SSLConnectionSocketFactory sslSocketFactory = mock(SSLConnectionSocketFactory.class);
        when(sslConfig.createSSLConnectionSocketFactory()).thenReturn(sslSocketFactory);

        CloseableHttpResponse mockResponse = mock(CloseableHttpResponse.class);
        when(httpClient.execute(any())).thenReturn(mockResponse);

        // Act
        CloseableHttpResponse response = httpClientService.executeRequest("https://localhost:8443/helloworld");

        // Assert
        Assert.notNull(response, "Response is null");
    }
}
