package com.boraecosystem.explorer.browser.service;

import com.boraecosystem.explorer.browser.config.DataSourceContextHolder;
import com.boraecosystem.explorer.browser.exception.InvalidAddressException;
import com.boraecosystem.explorer.browser.point.domain.TransactionSummary;
import com.boraecosystem.explorer.browser.point.repository.TransactionSummaryRepository;
import com.boraecosystem.explorer.browser.utils.AddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PointTransactionService {

    private final TransactionSummaryRepository transactionRepository;

    @Autowired
    public PointTransactionService(TransactionSummaryRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionSummary getTransactionSummaryByTransactionHash(String appId, String transactionHash) {
        DataSourceContextHolder.set(appId);
        TransactionSummary summary = transactionRepository.findByTransactionHash(transactionHash);
        DataSourceContextHolder.clear();
        return summary;
    }

    public Page<TransactionSummary> getTransactions(String appId, final Pageable pageable) {
        log.debug("APP_ID::{}", appId);
        DataSourceContextHolder.set(appId);
        Page<TransactionSummary> summaries = transactionRepository.findAllByOrderByBlockDateDescTransactionIndexDesc(pageable);
        DataSourceContextHolder.clear();
        return summaries;
    }

    public Page<TransactionSummary> getTransactionsByAddress(String appId, final String address, final Pageable pageable) {
        if (!AddressUtils.isValidAddress(address)) {
            throw new InvalidAddressException();
        }
        DataSourceContextHolder.set(appId);
        Page<TransactionSummary> summaries = transactionRepository.findAllByFromAddressOrToAddressOrderByBlockDateDescTransactionHashAsc(address, address, pageable);
        DataSourceContextHolder.clear();
        return summaries;
    }

}
