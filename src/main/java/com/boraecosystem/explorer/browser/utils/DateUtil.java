package com.boraecosystem.explorer.browser.utils;

import java.time.Duration;
import java.time.ZonedDateTime;

public class DateUtil {
    public static String differenceFromNow(ZonedDateTime from) {
        if (from == null) return null;

        Duration duration = Duration.between(from, ZonedDateTime.now());

        long days = duration.toDays();
        long hours = duration.toHours() - (duration.toDays() * 24);
        long mins = duration.toMinutes() - (duration.toHours() * 60);
        long secs = (duration.toMillis() - (duration.toMinutes() * 60000)) / 1000;

        return (days > 0 ? days + "d " : "") +
            (hours > 0 ? hours + "h " : "") +
            (mins > 0 ? mins + "m " : "") +
            secs + "s ago";
    }
}
