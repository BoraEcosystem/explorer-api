package com.boraecosystem.explorer.browser.config;

public class DataSourceContextHolder {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void set(String appId) {
        CONTEXT.set(appId);
    }

    public static String get() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
