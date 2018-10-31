package com.boraecosystem.explorer.browser.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Data
@Builder
public class PointStatus {
    private String appId;
    private String appName;
    private BigInteger holder;
    private BigInteger totalSupply;
    private Integer transferCount;
    private BigInteger currentBlock;
    private Integer tps;
    private ZonedDateTime latestBlockDate;
}
