package com.heshan.connector.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VisaServiceImpl implements VisaService {

    private static final Logger logger = LoggerFactory.getLogger(VisaServiceImpl.class);

    private final HttpClientService httpClientService;

    @Value("${visa.api.helloworld.url}")
    private String helloworldUrl;

    public VisaServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @Override
    public ResponseEntity<String> callHelloWrold() {
        logger.info("Calling VISA helloworld API");

        try (CloseableHttpResponse response = httpClientService.executeRequest(helloworldUrl)) {
            HttpEntity entity = response.getEntity();

            try (InputStream is = entity.getContent()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                int statusCode = response.getStatusLine().getStatusCode();
                String body = sb.toString();

                logger.info("VISA helloworld API response: status={}, body={}", statusCode, body);
                return ResponseEntity.status(statusCode).body(body);
            }
        } catch (Exception e) {
            logger.error("Error while calling VISA helloworld API", e);
            return ResponseEntity.status(500).body("Error while calling VISA helloworld API");
        }
    }
}
