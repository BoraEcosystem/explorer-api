package com.boraecosystem.explorer.browser.property;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;

@Data
public class Channel {
    private String appId;
    private String appName;
    private String contractAddress;
    private String nodeUrl;
    private HikariDataSource datasource;
}
