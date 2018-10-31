package com.boraecosystem.explorer.browser.model;

import com.boraecosystem.explorer.browser.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointBlockInfo {
    private BigInteger blockNo;
    private String blockHash;
    private BigInteger gasUsed;
    private BigInteger gasLimit;
    private BigInteger nonce;
    private BigInteger difficulty;
    private BigInteger totalDifficulty;
    private String miner;
    private String mixHash;
    private String parentHash;
    private String receiptsRoot;
    private String sha3Uncles;
    private BigInteger size;
    private String stateRoot;
    private String transactionsRoot;
    private Integer transactionCount;
    private Integer unclesCount;
    private String extraData;
    private String logsBloom;
    private ZonedDateTime blockDate;
    private String age;

    public String getAge() {
        return DateUtil.differenceFromNow(this.blockDate);
    }
}
