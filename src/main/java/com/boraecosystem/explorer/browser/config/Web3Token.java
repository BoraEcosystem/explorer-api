package com.boraecosystem.explorer.browser.config;

import com.boraecosystem.explorer.browser.property.ApplicationProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;

@Configuration
@ConfigurationProperties(prefix = "web3j")
@Slf4j
public class Web3Token {

    private final ApplicationProperty property;
    private final Web3Builder web3Builder;

    @Autowired
    public Web3Token(ApplicationProperty property, Web3Builder web3Builder) {
        this.property = property;
        this.web3Builder = web3Builder;
    }

    @Bean
    public Web3j web3j() {
        Web3jService web3jService = web3Builder.buildService(property);
        log.info("Building Web3J Instance for endpoint: " + property.getTokenNodeUrl());
        return Web3j.build(web3jService);
    }

}
