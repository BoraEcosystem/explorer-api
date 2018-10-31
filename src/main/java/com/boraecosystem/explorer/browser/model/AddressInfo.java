package com.boraecosystem.explorer.browser.model;

import lombok.Builder;
import lombok.Data;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
public class AddressInfo {
    private String address;
    private BigInteger balance;
    private String balanceInEther;

    public String getBalanceInEther() {
        if (this.balance == null) return null;
        return Convert.fromWei(new BigDecimal(this.balance), Convert.Unit.ETHER).toPlainString();
    }
}
