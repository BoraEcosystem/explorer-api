package com.boraecosystem.explorer.browser.config;

import com.boraecosystem.explorer.browser.property.ApplicationProperty;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Web3Builder {

    public Web3jService buildService(ApplicationProperty property) {
        return buildService(property.getTokenNodeUrl(), property);
    }

    public Web3jService buildService(String nodeUrl, ApplicationProperty property) {
        return new HttpService(nodeUrl, createOkHttpClient(property), false);
    }

    private OkHttpClient createOkHttpClient(ApplicationProperty property) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        configureLogging(builder);
        configureTimeouts(builder, property);
        return builder.build();
    }

    private void configureTimeouts(OkHttpClient.Builder builder, ApplicationProperty property) {
        Long tos = property.getHttpTimeoutSeconds();
        log.info("Web3J Timeout Configuration: {} seconds", tos);
        if (tos != null) {
            builder.connectTimeout(tos, TimeUnit.SECONDS);
            builder.readTimeout(tos, TimeUnit.SECONDS);  // Sets the socket timeout too
            builder.writeTimeout(tos, TimeUnit.SECONDS);
        }
    }

    private static void configureLogging(OkHttpClient.Builder builder) {
        if (log.isDebugEnabled()) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(log::debug);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
    }
}
