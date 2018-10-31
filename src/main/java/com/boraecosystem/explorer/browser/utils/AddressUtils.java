package com.boraecosystem.explorer.browser.utils;

import org.web3j.crypto.WalletUtils;

public class AddressUtils {
    public static boolean isValidAddress(String address) {
        if (address == null || !address.startsWith("0x")) return false;
        return WalletUtils.isValidAddress(address);
    }
}
