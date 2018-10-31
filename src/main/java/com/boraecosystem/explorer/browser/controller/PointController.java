package com.boraecosystem.explorer.browser.controller;

import com.boraecosystem.explorer.browser.contant.PageVariable;
import com.boraecosystem.explorer.browser.model.AddressInfo;
import com.boraecosystem.explorer.browser.model.PointBlockInfo;
import com.boraecosystem.explorer.browser.point.domain.BlockSummary;
import com.boraecosystem.explorer.browser.point.domain.TransactionSummary;
import com.boraecosystem.explorer.browser.property.Channel;
import com.boraecosystem.explorer.browser.service.ChannelService;
import com.boraecosystem.explorer.browser.service.PointBlockService;
import com.boraecosystem.explorer.browser.service.PointChainService;
import com.boraecosystem.explorer.browser.service.PointTransactionService;
import com.boraecosystem.explorer.browser.validator.AppConstraint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigInteger;

@Slf4j
@Api(value = "points", tags = {"BORA Point"})
@RestController
@RequestMapping("/points")
@CrossOrigin(origins = "*")
@Validated
public class PointController {

    private final PointTransactionService transactionService;
    private final PointBlockService blockService;
    private final PointChainService chainService;
    private final ChannelService channelService;

    public PointController(PointTransactionService transactionService, PointBlockService blockService, PointChainService chainService, ChannelService channelService) {
        this.transactionService = transactionService;
        this.blockService = blockService;
        this.chainService = chainService;
        this.channelService = channelService;
    }

    @ApiOperation(value = "Get Block list")
    @GetMapping("{appId}/blocks")
    public Page<BlockSummary> blocks(@PathVariable("appId") @AppConstraint String appId,
                                     @RequestParam(value = "pageSize", defaultValue = PageVariable.DEFAULT_PAGE_SIZE) Integer pageSize,
                                     @RequestParam(value = "page", defaultValue = PageVariable.DEFAULT_PAGE) Integer page) {
        return blockService.getBlockSummaries(appId, PageRequest.of(PageVariable.getPage(page), pageSize));
    }

    @ApiOperation(value = "Get Block by a block number")
    @GetMapping("{appId}/blocks/{blockNumber}")
    public PointBlockInfo getBlockByBlockNumber(@PathVariable("appId") @AppConstraint String appId, @PathVariable("blockNumber") BigInteger blockNumber) throws IOException {
        Channel channel = channelService.getChannel(appId);
        return chainService.getBlockInfoByBlockNumber(channel, blockNumber);
    }

    @ApiOperation(value = "Get Transaction list")
    @GetMapping("{appId}/txs")
    public Page<TransactionSummary> transactions(@PathVariable("appId") @AppConstraint String appId,
                                                 @RequestParam(value = "pageSize", defaultValue = PageVariable.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                 @RequestParam(value = "page", defaultValue = PageVariable.DEFAULT_PAGE) Integer page) {
        return transactionService.getTransactions(appId, PageRequest.of(PageVariable.getPage(page), pageSize));
    }

    @ApiOperation(value = "Get Transaction by a transaction hash")
    @GetMapping("{appId}/txs/{transactionHash}")
    public TransactionSummary getTransactionByHash(@PathVariable("appId") @AppConstraint String appId, @PathVariable("transactionHash") String transactionHash) {
        return transactionService.getTransactionSummaryByTransactionHash(appId, transactionHash);
    }

    @ApiOperation(value = "Get Transaction list by an address")
    @GetMapping("{appId}/addresses/{address}/txs")
    public Page<TransactionSummary> getTransactionsByAddress(
        @PathVariable("appId") @AppConstraint String appId,
        @PathVariable String address,
        @RequestParam(value = "pageSize", defaultValue = PageVariable.DEFAULT_PAGE_SIZE) Integer pageSize,
        @RequestParam(value = "page", defaultValue = PageVariable.DEFAULT_PAGE) Integer page) {
        return transactionService.getTransactionsByAddress(appId, address, PageRequest.of(PageVariable.getPage(page), pageSize));
    }

    @ApiOperation(value = "Get information for an address such as balance and so on")
    @GetMapping("{appId}/addresses/{address}")
    public AddressInfo getInfoByAddress(
        @PathVariable("appId") @AppConstraint String appId,
        @PathVariable("address") String address) {

        Channel channel = channelService.getChannel(appId);
        return AddressInfo.builder()
            .address(address)
            .balance(chainService.balanceOf(channel, address))
            .build();
    }
}
