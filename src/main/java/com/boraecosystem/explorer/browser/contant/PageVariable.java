package com.boraecosystem.explorer.browser.contant;

public class PageVariable {
    public static final String DEFAULT_PAGE = "1";
    public static final String DEFAULT_PAGE_SIZE = "10";

    public static int getPage(int page) {
        if (page == 0) return 0;
        return page - 1;
    }
}
