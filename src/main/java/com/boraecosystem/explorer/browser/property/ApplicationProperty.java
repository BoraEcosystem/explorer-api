package com.boraecosystem.explorer.browser.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperty {
    private List<Channel> channels;
    private String tokenAddress;
    private String tokenNodeUrl;
    private Long httpTimeoutSeconds;

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public String getTokenAddress() {
        return tokenAddress;
    }

    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }

    public String getTokenNodeUrl() {
        return tokenNodeUrl;
    }

    public void setTokenNodeUrl(String tokenNodeUrl) {
        this.tokenNodeUrl = tokenNodeUrl;
    }

    public Long getHttpTimeoutSeconds() {
        return httpTimeoutSeconds;
    }

    public void setHttpTimeoutSeconds(Long httpTimeoutSeconds) {
        this.httpTimeoutSeconds = httpTimeoutSeconds;
    }
}
