package com.boraecosystem.explorer.browser.service;

import com.boraecosystem.explorer.browser.model.AppInfo;
import com.boraecosystem.explorer.browser.property.ApplicationProperty;
import com.boraecosystem.explorer.browser.property.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ChannelService {

    private final ApplicationProperty applicationProperty;

    @Autowired
    public ChannelService(ApplicationProperty applicationProperty) {
        this.applicationProperty = applicationProperty;
    }

    public Channel getChannel(String serviceCode) {
        List<Channel> channels = applicationProperty.getChannels();
        for (Channel channel : channels) {
            if (serviceCode.equals(channel.getAppId())) {
                return channel;
            }
        }
        throw new RuntimeException();
    }

    public List<AppInfo> getAppInfos() {
        List<Channel> channels = applicationProperty.getChannels();
        List<AppInfo> appInfos = new LinkedList<>();
        for (Channel channel : channels) {
            appInfos.add(
                AppInfo.builder()
                    .appId(channel.getAppId())
                    .appName(channel.getAppName())
                    .tokenAddress(channel.getContractAddress())
                    .build()
            );
        }
        return appInfos;
    }

    public List<Channel> getChannels() {
        return applicationProperty.getChannels();
    }
}
