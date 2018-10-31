package com.boraecosystem.explorer.crawler.service;

import com.boraecosystem.explorer.browser.property.ApplicationProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@SpringBootConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class Web3TokenIntanceTest {
    @Test
    public void getInstanceOf() {
    }

    @Autowired

    @Test
    public void cha() {
        ApplicationProperty applicationProperty = new ApplicationProperty();
        applicationProperty.getChannels();
    }

}
