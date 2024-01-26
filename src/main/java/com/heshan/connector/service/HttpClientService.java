package com.heshan.connector.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.heshan.connector.config.SSLConfig;

@Service
public class HttpClientService {

    @Value("${visa.api.authentication.user.id}")
    private String authorizationUserId;

    @Value("${visa.api.authentication.password}")
    private String authorizationPassword;

    private final SSLConfig sslConfig;

    public HttpClientService(SSLConfig sslConfig) {
        this.sslConfig = sslConfig;
    }

    public CloseableHttpResponse executeRequest(String url) throws Exception {
        SSLConnectionSocketFactory sslSocketFactory = sslConfig.createSSLConnectionSocketFactory();
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();
        
        HttpGet httpGet = new HttpGet(url);
        // Add necessary headers and authentication
        setHeaders(httpGet);

        return httpClient.execute(httpGet);
    }

    private void setHeaders(HttpGet httpGet) {
        httpGet.addHeader("Accept", "application/json");
        httpGet.addHeader("Content-Type", "application/json");

        // THIS IS EXAMPLE ONLY how will user_id and password look like
        // userId = "1WM2TT4IHPXC8DQ5I3CH21n1rEBGK-Eyv_oLdzE2VZpDqRn_U";
        // password = "19JRVdej9";
        String userId = authorizationUserId;
        String password = authorizationPassword;

        String auth = userId + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeaderValue = "Basic " + new String(encodedAuth);
        httpGet.addHeader("Authorization", authHeaderValue);
    }
}
