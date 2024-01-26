package com.heshan.connector.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VisaServiceImplTests {

    @Mock
    private HttpClientService httpClientService;

    @InjectMocks
    private VisaServiceImpl visaService;

    @Test
    public void testCallHelloWorld_shouldReturnResponse() throws Exception {
        // Mock dependencies
        CloseableHttpResponse mockResponse = mock(CloseableHttpResponse.class);
        HttpEntity mockEntity = mock(HttpEntity.class);
        when(httpClientService.executeRequest(anyString())).thenReturn(mockResponse);
        when(mockResponse.getEntity()).thenReturn(mockEntity);
        when(mockEntity.getContent()).thenReturn(new ByteArrayInputStream("Test response".getBytes()));

        StatusLine statusLine = mock(StatusLine.class);
        when(mockResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);

        // Call the method under test
        ResponseEntity<String> responseEntity = visaService.callHelloWrold();

        // Assertions
        assertEquals(200, responseEntity.getStatusCode());
        assertEquals("Test response", responseEntity.getBody());
    }
}
