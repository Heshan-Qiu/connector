package com.heshan.connector.config;

import java.io.File;
import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SSLConfig {

    @Value("${ssl.key-store-path}")
    private String keyStorePath;

    @Value("${ssl.key-store-password}")
    private String keyStorePassword;

    @Value("${ssl.private-key-password}")
    private String privateKeyPassword;

    private SSLContext createSSLContext() throws Exception {
        // Load client certificate into key store
        return SSLContexts.custom()
            .loadKeyMaterial(new File(keyStorePath), keyStorePassword.toCharArray(), privateKeyPassword.toCharArray())
            .loadTrustMaterial(new File(keyStorePath), keyStorePassword.toCharArray())
            .build();
    }

    public SSLConnectionSocketFactory createSSLConnectionSocketFactory() throws Exception {
        // Allow TLSv1.2 protocol only
        return new SSLConnectionSocketFactory(createSSLContext(), 
            new String[] { "TLSv1.2" }, 
            null, 
            SSLConnectionSocketFactory.getDefaultHostnameVerifier());
    }
}
