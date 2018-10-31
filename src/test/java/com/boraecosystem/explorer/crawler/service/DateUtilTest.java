package com.boraecosystem.explorer.crawler.service;

import com.boraecosystem.explorer.browser.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DateUtilTest {
    @Test
    public void time() {
        ZonedDateTime t1 = ZonedDateTime.of(LocalDateTime.of(2018, 10, 2, 0, 0, 0), ZoneId.systemDefault());

        String difference = DateUtil.differenceFromNow(t1);
        System.out.println(difference);
    }
}
