package com.boraecosystem.explorer.browser.config;

import com.boraecosystem.explorer.browser.property.ApplicationProperty;
import com.boraecosystem.explorer.browser.property.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class Web3Point {

    private final ApplicationProperty property;
    private final Web3Builder web3Builder;

    private final Map<String, Web3j> web3Map = new HashMap<>();

    @Autowired
    public Web3Point(ApplicationProperty property, Web3Builder web3Builder) {
        this.property = property;
        this.web3Builder = web3Builder;
    }

    @Bean
    public Map<String, Web3j> web3PointInit() {
        final List<Channel> channels = property.getChannels();
        for (Channel channel : channels) {
            log.info("Initializing Web3J Instance Of POINT for Service {}.", channel.getAppId());
            Web3jService web3jService = web3Builder.buildService(channel.getNodeUrl(), property);
            web3Map.put(channel.getAppId(), Web3j.build(web3jService));
            log.info("Building Web3J Instance for endpoint: " + channel.getNodeUrl());
        }
        return web3Map;
    }

    public Web3j get(String appId) {
        return web3Map.get(appId);
    }

}
