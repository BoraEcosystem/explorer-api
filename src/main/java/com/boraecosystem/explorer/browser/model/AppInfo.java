package com.boraecosystem.explorer.browser.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppInfo {
    private String appId;
    private String appName;
    private String tokenAddress;
}
