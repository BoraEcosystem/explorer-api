package com.boraecosystem.explorer.browser.controller;

import com.boraecosystem.explorer.browser.model.AppInfo;
import com.boraecosystem.explorer.browser.service.ChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(value = "apps", tags = {"Applications"})
@RestController
@RequestMapping("/apps")
@CrossOrigin(origins = "*")
public class AppController {

    private final ChannelService channelService;

    @Autowired
    public AppController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @ApiOperation(value = "Get Application list using BORA Point")
    @GetMapping
    public List<AppInfo> appInfos() {
        return channelService.getAppInfos();
    }

}
